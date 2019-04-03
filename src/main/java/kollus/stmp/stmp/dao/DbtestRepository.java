package kollus.stmp.stmp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DbtestRepository extends CrudRepository<DbtestEntity, Long> {
    @Query("SELECT a FROM test_table a")
    List<DbtestEntity> findByRange();
}
