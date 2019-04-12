package kollus.stmp.stmp.component;

import kollus.stmp.stmp.KollusConfig;
import kollus.stmp.stmp.dao.*;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ScheduleComponent implements Job {
   /* @Autowired
    private KollusConfig kollusConfig;
    @Autowired
    private DbtestRepository dbtestRepository;
    @Autowired
    private DbCustomerRepository dbCustomerRepository;
    @Autowired
    private DbCustomerCodeRepository dbCustomerCodeRepository;*/

    public ScheduleComponent() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       /* List<HashMap<String, String>> customerList = new ArrayList<HashMap<String, String>>();
        String toUser = "";
        List<DbCustomerCodeEntity> codeList = dbCustomerCodeRepository.selectCustomerCode();
        for(DbCustomerCodeEntity item : codeList){
            HashMap<String, String> customItem = new HashMap<String, String>();
            List<DbCustomerEntity> csItems = dbCustomerRepository.findCustomerKey(item.getCustomer_key());

            if(csItems.size() >0 ){
                for(DbCustomerEntity csEntity : csItems){
                    toUser += csEntity.getCustomer_email()+",";
                }
                customItem.put("key", item.getCustomer_key());
                customItem.put("email", toUser);
                customerList.add(customItem);
            }
            toUser = "";
        }*/
    }
}