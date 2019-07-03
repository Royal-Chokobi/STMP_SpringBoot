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

    @Query(value = "SELECT res FROM  smtp_reservation res WHERE res.group_code = :group_code AND res.state = 'R' ")
    List<DbReservationEntity> getScheduleSendList(@Param("group_code") String group_code);

    @Query(value = "SELECT GROUP_CONCAT(tb_res.customer separator ',') AS customer, GROUP_CONCAT(tb_res.state separator ',') AS state, tb_res.email_form " +
            " FROM smtp_reservation tb_res WHERE tb_res.group_code = :group_code group by tb_res.email_form ", nativeQuery = true)
    List<Object[]> getSendMailDetailData(@Param("group_code") String group_code);

    @Query(value = "SELECT tb_res.reservation_code, tb_res.customer, tb_res.email_title, tb_res.customer_address, tb_res.state  FROM smtp_reservation tb_res WHERE tb_res.group_code = :group_code", nativeQuery = true)
    List<Object[]> getReservationDetailData(@Param("group_code") String group_code);

    @Modifying
    @Transactional
    @Query("UPDATE smtp_reservation res SET res.state = :state WHERE res.reservation_code = :reservation_code")
    Integer cancelReservationData(@Param("state") String state, @Param("reservation_code") String reservation_code);

    @Query(value = "SELECT COUNT(*) as cnt FROM smtp_reservation res WHERE res.group_code = :group_code AND res.state = 'R' ", nativeQuery = true)
    Integer countReservationState(@Param("group_code") String group_code);

}
