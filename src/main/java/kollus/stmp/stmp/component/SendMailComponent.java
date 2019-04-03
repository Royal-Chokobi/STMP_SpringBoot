package kollus.stmp.stmp.component;

import kollus.stmp.stmp.KollusConfig;
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
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Component
public class SendMailComponent {
    @Autowired
    private KollusConfig kollusConfig;
    @Autowired
    private DbtestRepository dbtestRepository;

    public SendMailComponent(){}

    public void test123(){
        List<DbtestEntity> items = dbtestRepository.findByRange();
        System.out.println(items);
    }
    public String getHTMLMailForm(String textBody){

        String mailForm = "<div style = 'width: 900px;'>";
        mailForm+=textBody;
        mailForm+="</div>";

        return mailForm;
    }

    public void sendMailingSystem(String sendMailHTML){

        kollusConfig.toString();

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

        Authenticator auth = new MailAuthentication(smtpMailAddress, smtpSecretKey);
        Session session = Session.getInstance(prop, auth);
        MimeMessage msg = new MimeMessage(session);
        String toUser = "jaeyoon.lee@catenoid.net,sinsax@naver.com";
        try{
            msg.setSentDate(new Date()); //편지보낸시간
            InternetAddress from = new InternetAddress(smtpMailAddress);
            msg.setFrom(from); // 이메일 발신자

            /** 이메일 수신자 부분 **/
            msg.addRecipients(Message.RecipientType.CC, toUser);

            msg.setSubject("메일 전송 테스트", "UTF-8");
           // String contents = "";

            msg.setContent(sendMailHTML,"text/html;charset=euc-kr");

            javax.mail.Transport.send(msg);

        }catch (AddressException addr_e) {
            addr_e.printStackTrace();
        }catch (MessagingException msg_e) {
            msg_e.printStackTrace();
        }
    }

}