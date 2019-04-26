package kollus.stmp.stmp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

public interface DbCustomerCodeRepository extends CrudRepository<DbCustomerCodeEntity, Long> {
    @Query("SELECT tb_code FROM customer_code tb_code")
    List<DbCustomerCodeEntity> selectCustomerCode();

   @Query(
            value = "SELECT tb_code.customer_key,tb_code.customer, GROUP_CONCAT(tb_info.customer_email separator ',') AS customer_email FROM customer_code tb_code " +
                    "INNER JOIN  customer_info tb_info ON tb_info.customer_key = tb_code.customer_key " +
                    "GROUP BY tb_code.customer ORDER BY tb_code.customer_key ASC"
            , nativeQuery = true
    )
   List<Object[]> GetCustomerEmail();

}
