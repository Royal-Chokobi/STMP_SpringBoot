package kollus.stmp.stmp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DbCustomerCodeRepository extends CrudRepository<DbCustomerCodeEntity, Long> {
    @Query("SELECT tb_code FROM customer_code tb_code")
    List<DbCustomerCodeEntity> selectCustomerCode();
}
