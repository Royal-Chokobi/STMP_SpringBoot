package kollus.stmp.stmp.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DbCustomerRepository extends CrudRepository<DbCustomerEntity, Long> {
    @Query("SELECT tb_info FROM customer_info tb_info")
    List<DbCustomerEntity> selectCustomerInformation();
    @Query("SELECT tb_info FROM customer_info tb_info WHERE tb_info.customer_key = :customer_key")
    List<DbCustomerEntity> findCustomerKey(@Param("customer_key") String customer_key);
    @Modifying
    @Transactional
    @Query(value = "UPDATE customer_info tb_info SET tb_info.customer_name = :customer_name, tb_info.customer_email = :customer_email WHERE tb_info.customer_index = :customer_index ", nativeQuery = true)
    Integer updateCustomerInfo(@Param("customer_name") String customer_name, @Param("customer_email") String customer_email, @Param("customer_index") String customer_index);
}
