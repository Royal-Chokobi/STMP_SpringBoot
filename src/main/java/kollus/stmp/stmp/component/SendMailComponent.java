package kollus.stmp.stmp.component;

import kollus.stmp.stmp.KollusConfig;
import kollus.stmp.stmp.dao.*;
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
public class SendMailComponent {
    @Autowired
    private KollusConfig kollusConfig;
    @Autowired
    private DbCustomerRepository dbCustomerRepository;
    @Autowired
    private DbCustomerCodeRepository dbCustomerCodeRepository;
    @Autowired
    private DbReservationRepository dbReservationRepository;

    public SendMailComponent(){}

    public List<DbReservationEntity> getEmailSendList(){

        List<DbReservationEntity> resultList = dbReservationRepository.getSendList();
       /* List<HashMap<String, String>> sendlist = new ArrayList<>();
        for (Object[] borderTypes: resultList) {
            HashMap<String, String> results = new HashMap<String, String>();
            results.put("reservation_code", (String)borderTypes[0]);
            results.put("group_code", (String)borderTypes[1]);
            results.put("email_title", (String)borderTypes[2]);
            results.put("reservation_date", borderTypes[3].toString());
            results.put("sys_send_date", borderTypes[4].toString());
            results.put("state", (String)borderTypes[5]);
            sendlist.add(results);
        }*/

        return resultList;
    }

    public String getHTMLMailForm(String textBody){

        String mailForm = "<div style = 'width: 900px;'>";
        mailForm+=textBody;
        mailForm+="</div>";

        return mailForm;
    }

    public String setSchedule(String sendMailHTML, List<HashMap<String, String>> costomerList){

        return "";
    }

    public HashMap<String, String> getCustomerCode(String type, String key){

      //  List<HashMap<String, String>> customerList = new ArrayList<HashMap<String, String>>();
      //  String toUser = "";
        List<Object[]> resultList = dbCustomerCodeRepository.GetCustomerEmail();
        /*for(HashMap<String, String> item : codeList){

            System.out.println(item.toString());
      //      System.out.println(item.get("customer_email"));
        }*/
        HashMap<String, String> results = new HashMap<String, String>();
        for (Object[] borderTypes: resultList) {
            results.put((String)borderTypes[0], (String)borderTypes[1]);
            // key = customer , value = customer_email
        }

        //Set<String> aaa= results.;
       // Iterator iterator = aaa.iterator();

      //  System.out.println(aaa);

        /*if(type.equals("all")){
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
            }
        }else{
            if(!key.isEmpty()){
                String[] csKey= key.split(",");
                for(String item :  csKey){
                    HashMap<String, String> customItem = new HashMap<String, String>();
                    List<DbCustomerEntity> csItems = dbCustomerRepository.findCustomerKey(item);

                    if(csItems.size() >0 ){
                        for(DbCustomerEntity csEntity : csItems){
                            toUser += csEntity.getCustomer_email()+",";
                        }
                        customItem.put("key", item);
                        customItem.put("email", toUser);
                        customerList.add(customItem);
                    }
                    toUser = "";
                }
            }
        }*/

        return results;
    }

    public HashMap<String, Object> sendMailingSystem(String sendMailHTML, List<HashMap<String, String>> costomerList){
       // List<DbCustomerEntity> items = dbCustomerRepository.selectCustomerInformation();
        HashMap<String, Object> result = new HashMap<String, Object>();
       // kollusConfig.toString();

        if(costomerList.size() <= 0){
            result.put("error", 1);
            result.put("message", "등록된 고객정보가 없습니다.");
            return result;
        }

        String smtpHost = kollusConfig.getHost();
        String smtpPort = "587";
        String smtpProtocol = kollusConfig.getProtocol();
        String smtpMailAddress = kollusConfig.getMailAddress();
        String smtpSecretKey = kollusConfig.getSecretKey();

        if(smtpProtocol.equals("https") && !smtpProtocol.isEmpty()){
            smtpPort = "465"; // ssl 일 경우.
        }

        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", smtpHost);
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.port", smtpPort);

        for(HashMap<String, String> mapList:costomerList){
            Authenticator auth = new MailAuthentication(smtpMailAddress, smtpSecretKey);
            Session session = Session.getInstance(prop, auth);

            String toUser = mapList.get("email");

            MimeMessage msg = new MimeMessage(session);
            // String toUser = "jaeyoon.lee@catenoid.net,sinsax@naver.com";
            try{
                msg.setSentDate(new Date());
                InternetAddress from = new InternetAddress(smtpMailAddress);
                msg.setFrom(from);

                msg.addRecipients(Message.RecipientType.CC, toUser);
                msg.setSubject("메일 전송 단위 테스트", "UTF-8");
                msg.setContent(sendMailHTML,"text/html;charset=euc-kr");
                javax.mail.Transport.send(msg);

               // result.put("error", 0);
              //  result.put("message", "전송에 성공했습니다.");
            }catch (AddressException addr_e) {
               // result.put("error", 1);
               // result.put("message", "-addr system error 전송에 실패했습니다.");
                addr_e.printStackTrace();
            }catch (MessagingException msg_e) {
              //  result.put("error", 1);
              //  result.put("message", "-msg system error 전송에 실패했습니다.");
                msg_e.printStackTrace();
            }

        }

        result.put("error", 0);
        result.put("message", "전송에 성공했습니다.");

        return result;
    }


}