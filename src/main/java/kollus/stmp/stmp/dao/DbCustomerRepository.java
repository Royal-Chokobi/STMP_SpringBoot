package kollus.stmp.stmp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DbCustomerRepository extends CrudRepository<DbCustomerEntity, Long> {
    @Query("SELECT tb_info FROM customer_information tb_info")
    List<DbCustomerEntity> selectCustomerInformation();
    @Query("SELECT tb_info FROM customer_information tb_info WHERE tb_info.customer_key = :customer_key")
    List<DbCustomerEntity> findCustomerKey(@Param("customer_key") String customer_key);

}
