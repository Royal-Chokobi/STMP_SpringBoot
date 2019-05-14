package kollus.stmp.stmp.component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

    public HashMap<String, String> editCustomerNM(String editNm, String customerCode){
        HashMap<String, String> results = new HashMap<String, String>();
        int updateState = dbCustomerCodeRepository.updateCustomerName(editNm, customerCode);

        if(updateState < 1){
            results.put("message", "error! 수정에 실패했습니다.");
        }else{
            results.put("message", "정상적으로 수정 되었습니다.");
        }

        return results;
    }

    public HashMap<String, String> editCustomerInfo(String index, String customerNM, String customerEmail){
        HashMap<String, String> results = new HashMap<String, String>();
        int updateState = dbCustomerRepository.updateCustomerInfo(customerNM, customerEmail, index);

        if(updateState < 1){
            results.put("message", "error! 수정에 실패했습니다.");
        }else{
            results.put("message", "정상적으로 수정 되었습니다.");
        }

        return results;
    }

    public HashMap<String, String> delCustomerInfo(String index){
        HashMap<String, String> results = new HashMap<String, String>();
        dbCustomerRepository.deleteById(Long.parseLong(index));
        results.put("message", "정상적으로 삭제 되었습니다.");

        return results;
    }

    public HashMap<String, String> delCustomer(String customerCode){
        HashMap<String, String> results = new HashMap<String, String>();

        dbCustomerRepository.deleteCustomerInfo(customerCode);
        dbCustomerCodeRepository.deleteCustomer(customerCode);

        results.put("message", "정상적으로 삭제 되었습니다.");

        return results;
    }

    public String insertCustomerCodeInfo(String customer_name){

        DbCustomerCodeEntity cusCodeEnt = new DbCustomerCodeEntity();
        String cusCode = dbCustomerCodeRepository.getNewCustomerKey();
        cusCodeEnt.setCustomer_key(cusCode);
        cusCodeEnt.setCustomer(customer_name);
        dbCustomerCodeRepository.save(cusCodeEnt);

        return cusCode;
    }

    public HashMap<String, String> insertCustomerInfo(String manager_info, String cusCode){

        HashMap<String, String> results = new HashMap<String, String>();
        JsonParser jsonParser = new JsonParser();
        JsonArray info_jsonArray = (JsonArray) jsonParser.parse(manager_info);
        List<DbCustomerEntity> cusEntarr = new ArrayList<>();

        for (int i = 0; i < info_jsonArray.size(); i++) {
            JsonObject item_obj = (JsonObject) info_jsonArray.get(i);
            String manager_name = item_obj.get("manager_name").getAsString().trim();
            String manager_email = item_obj.get("manager_email").getAsString().trim();

            if(manager_email != null && manager_name != null){
                DbCustomerEntity cusEnt = new DbCustomerEntity();
                cusEnt.setCustomer_key(cusCode);
                cusEnt.setCustomer_name(manager_name);
                cusEnt.setCustomer_email(manager_email);
                cusEntarr.add(cusEnt);
            }
        }

        dbCustomerRepository.saveAll(cusEntarr);

        results.put("message", "정상적으로 등록 되었습니다.");

        return results;
    }
}