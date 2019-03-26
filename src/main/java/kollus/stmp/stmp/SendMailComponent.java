package kollus.stmp.stmp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMailComponent {
    @Autowired
    private KollusConfig kollusConfig;

   /* private final String mail_Address;
    private final String mail_Host;
    private final String mail_Protocol;
    private final String mail_SecretKey;

    public SendMailComponent(){
        this.mail_Address = kollusConfig.getMailAddress();
        this.mail_Host = kollusConfig.getHost();
        this.mail_Protocol = kollusConfig.getProtocol();
        this.mail_SecretKey = kollusConfig.getSecretKey();
    }*/

    public void sendMailingSystem(){
        System.out.println("asdfasdf");
        kollusConfig.toString();
        /*System.out.println(mail_Address);
        System.out.println(mail_Host);
        System.out.println(mail_Protocol);
        System.out.println(mail_SecretKey);*/
    }

}
