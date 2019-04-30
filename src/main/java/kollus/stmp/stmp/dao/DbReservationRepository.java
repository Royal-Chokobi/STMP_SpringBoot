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

    @Query(value = "SELECT res FROM  smtp_reservation res WHERE res.group_code = :group_code AND res.state not in('C', 'Y')")
    List<DbReservationEntity> getScheduleSendList(@Param("group_code") String group_code);

}
