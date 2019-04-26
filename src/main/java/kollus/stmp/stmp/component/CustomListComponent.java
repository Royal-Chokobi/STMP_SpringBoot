package kollus.stmp.stmp.component;

import kollus.stmp.stmp.KollusConfig;
import kollus.stmp.stmp.dao.DbCustomerCodeRepository;
import kollus.stmp.stmp.dao.DbCustomerRepository;
import kollus.stmp.stmp.dao.DbtestEntity;
import kollus.stmp.stmp.dao.DbtestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Component
public class CustomListComponent {

    @Autowired
    private DbtestRepository dbtestRepository;
    @Autowired
    private DbCustomerRepository dbCustomerRepository;
    @Autowired
    private DbCustomerCodeRepository dbCustomerCodeRepository;

    public CustomListComponent(){}

    public void test123(){
        List<DbtestEntity> items = dbtestRepository.findByRange();
        System.out.println(items);
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

}