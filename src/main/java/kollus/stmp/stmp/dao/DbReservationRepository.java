package kollus.stmp.stmp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DbReservationRepository extends JpaRepository<DbReservationEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE smtp_reservation res SET res.state = :state, res.sys_send_date = CURRENT_TIMESTAMP WHERE res.reservation_code = :reservation_code")
    Integer updateReservationData(@Param("reservation_code") String reservation_code, @Param("state") String state);

    @Query(value = "select CONCAT('G',(SELECT LPAD(COUNT(*)+1,5,'0') FROM smtp_reservation)) AS group_code", nativeQuery = true)
    String getGroupCode();

    @Query(value = "SELECT res.reservation_code, res.group_code, res.email_title, res.reservation_date, res.sys_send_date ,res.state FROM smtp_reservation res order by res.sysdate desc", nativeQuery = true)
    List<Object[]> getSendList();

}
