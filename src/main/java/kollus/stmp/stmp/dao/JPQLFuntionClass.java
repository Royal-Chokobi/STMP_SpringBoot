package kollus.stmp.stmp.dao;

import kollus.stmp.stmp.KollusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import javax.persistence.*;

public class JPQLFuntionClass {
    @Autowired
    private EntityManager em;
    @Autowired
    private TypedQuery query;

    public JPQLFuntionClass(){}

    public void GetCustomerEmail(){
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("smtp");
        em = entityManagerFactory.createEntityManager();
       // String queryString = "SELECT a FROM customer_code a ";
        TypedQuery<DbCustomerCodeEntity> query = em.createQuery("SELECT a FROM customer_code a ", DbCustomerCodeEntity.class);
        List<DbCustomerCodeEntity> test= query.getResultList();
        System.out.println("test");
        for(DbCustomerCodeEntity p : test) {
            System.out.println(p.toString());
        }
       // return null;
    }
}
