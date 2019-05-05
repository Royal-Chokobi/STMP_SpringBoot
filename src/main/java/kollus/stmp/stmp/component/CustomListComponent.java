package kollus.stmp.stmp.component;

import kollus.stmp.stmp.dao.DbCustomerCodeEntity;
import kollus.stmp.stmp.dao.DbCustomerCodeRepository;
import kollus.stmp.stmp.dao.DbCustomerEntity;
import kollus.stmp.stmp.dao.DbCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CustomListComponent {

    @Autowired
    private DbCustomerRepository dbCustomerRepository;
    @Autowired
    private DbCustomerCodeRepository dbCustomerCodeRepository;

    public CustomListComponent(){}

    public void test123(){

    }

    public List<HashMap<String, String>> getCustomerList(){
        List<Object[]> resultList = dbCustomerCodeRepository.GetCustomerEmail();
        List<HashMap<String, String>> cmlist = new ArrayList<>();

        for (Object[] borderTypes: resultList) {
            HashMap<String, String> results = new HashMap<String, String>();
            results.put("customerCode", (String)borderTypes[0]);
            results.put("customerNM", (String)borderTypes[1]);
            results.put("customerEmail", (String)borderTypes[2]);
            cmlist.add(results);
        }

        return cmlist;
    }

    public List<DbCustomerEntity> getDetailCustomerInfo(String customerCode){
        List<DbCustomerEntity> results = dbCustomerRepository.findCustomerKey(customerCode);

        return results;
    }

}