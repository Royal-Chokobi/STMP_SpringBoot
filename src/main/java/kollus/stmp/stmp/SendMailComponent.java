package kollus.stmp.stmp;

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
import java.util.Properties;

@Component
public class SendMailComponent {
    @Autowired
    private KollusConfig kollusConfig;

    public SendMailComponent(){}

    /*public String getHTMLMailForm() throws Exception{
        File fileps = new File("src/main/resources/templates/mailForm/sendMailContent.html");

        Path path = Paths.get(fileps.getCanonicalPath());
        Charset cs = StandardCharsets.UTF_8;
        List<String> list = new ArrayList<String>();
        try{
            list = Files.readAllLines(path,cs);
        }catch(IOException e){
            e.printStackTrace();
        }
        for(String readLine : list){
            System.out.println(readLine);
        }

        return "";
    }*/

    public void sendMailingSystem(String toUser){

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
        Session session = Session.getDefaultInstance(prop, auth);
        MimeMessage msg = new MimeMessage(session);

        try{
            msg.setSentDate(new Date()); //편지보낸시간
            InternetAddress from = new InternetAddress(smtpMailAddress);
            msg.setFrom(from); // 이메일 발신자

            /** 이메일 수신자 부분 **/
            msg.addRecipients(Message.RecipientType.CC, toUser);

            msg.setSubject("메일 전송 테스트", "UTF-8");
            String contents = "";

            msg.setContent(contents,"text/html;charset=euc-kr");

            javax.mail.Transport.send(msg);

        }catch (AddressException addr_e) {
            addr_e.printStackTrace();
        }catch (MessagingException msg_e) {
            msg_e.printStackTrace();
        }
    }

}