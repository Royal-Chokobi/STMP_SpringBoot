package kollus.stmp.stmp.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

public interface DbCustomerCodeRepository extends CrudRepository<DbCustomerCodeEntity, Long> {
    @Query("SELECT tb_code FROM customer_code tb_code")
    List<DbCustomerCodeEntity> selectCustomerCode();

   @Query(
            value = "SELECT tb_code.customer_key,tb_code.customer, GROUP_CONCAT(tb_info.customer_email separator ', ') AS customer_email FROM customer_code tb_code " +
                    "LEFT JOIN  customer_info tb_info ON tb_info.customer_key = tb_code.customer_key " +
                    "GROUP BY tb_code.customer_key, tb_code.customer ORDER BY tb_code.customer_key ASC"
            , nativeQuery = true
    )
   List<Object[]> GetCustomerEmail();

   @Query(value = "select CONCAT('C',(SELECT LPAD(COUNT(*)+1,5,'0') FROM customer_code)) AS customer_key", nativeQuery = true)
   String getNewCustomerKey();

   @Modifying
   @Transactional
   @Query(value = "UPDATE customer_code cus_code SET cus_code.customer = :customer WHERE cus_code.customer_key = :customer_key ", nativeQuery = true)
   Integer updateCustomerName(@Param("customer") String customer, @Param("customer_key") String customer_key);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customer_code WHERE customer_key= :customer_key ", nativeQuery = true)
    Integer deleteCustomer(@Param("customer_key") String customer_key);
}
