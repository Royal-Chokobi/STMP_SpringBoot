package kollus.stmp.stmp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SendMailComponent {
    @Autowired
    private KollusConfig kollusConfig;

    public SendMailComponent(){}

    public void sendMailingSystem(){

        System.out.println("asdfasdf");
        kollusConfig.toString();
        String aa = kollusConfig.getMailAddress();
        System.out.println(aa);

        String smtpHost = kollusConfig.getHost();
        String smtpProtocol = kollusConfig.getProtocol();
        String smtpMailAddress = kollusConfig.getMailAddress();


        Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");     // gmail은 무조건 true 고정
        p.put("mail.smtp.host", "smtp.gmail.com");      // smtp 서버 주소
        p.put("mail.smtp.auth","true");                 // gmail은 무조건 true 고정
        p.put("mail.smtp.port", "587");                 // gmail 포트 ssl은 465

        Authenticator auth = new MyAuthentication();

        //session 생성 및  MimeMessage생성
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);

        try{
            //편지보낸시간
            msg.setSentDate(new Date());
            InternetAddress from = new InternetAddress() ;
            from = new InternetAddress("@catenoid.net");

            // 이메일 발신자
            msg.setFrom(from);

            // 이메일 수신자
            InternetAddress[] to = new InternetAddress[10];
            to[0] = new InternetAddress("");
            to[1] = new InternetAddress("");
            msg.setRecipient(Message.RecipientType.TO, to[0]);
            msg.addRecipient(Message.RecipientType.TO, to[1]);

            // 이메일 제목
            msg.setSubject("메일 전송 테스트", "UTF-8");

            // 이메일 내용
            msg.setText("내용123123", "UTF-8");

            // 이메일 헤더
            msg.setHeader("content-Type", "text/html");

            //메일보내기
            javax.mail.Transport.send(msg);

        }catch (AddressException addr_e) {
            addr_e.printStackTrace();
        }catch (MessagingException msg_e) {
            msg_e.printStackTrace();
        }

    }

}
