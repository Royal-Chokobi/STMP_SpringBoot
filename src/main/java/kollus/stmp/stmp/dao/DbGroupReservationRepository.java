package kollus.stmp.stmp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DbGroupReservationRepository extends JpaRepository<DbGroupReservationEntity, Long> {
    @Query(value = "select CONCAT('G',(SELECT LPAD(COUNT(*)+1,5,'0') FROM smtp_reservation_group)) AS group_code", nativeQuery = true)
    String getGroupCode();
    @Query(value = "SELECT * FROM smtp_reservation_group res_g order by res_g.sysdate desc", nativeQuery = true)
    List<DbGroupReservationEntity> getSendList();
    @Modifying
    @Transactional
    @Query("UPDATE smtp_reservation_group res_g SET res_g.state = :state, res_g.sys_send_date = CURRENT_TIMESTAMP WHERE res_g.group_code = :group_code")
    Integer updateGroupReservationData(@Param("group_code") String group_code, @Param("state") String state);
}
