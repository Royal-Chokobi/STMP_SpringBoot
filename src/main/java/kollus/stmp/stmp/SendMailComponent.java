package kollus.stmp.stmp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMailComponent {
    @Autowired
    private KollusConfig kollusConfig;

    public void sendMail(){
        String mail_Address = kollusConfig.getMailAddress();
        String mail_Host = kollusConfig.getHost();
        String mail_Protocol = kollusConfig.getProtocol();
        String mail_SecretKey = kollusConfig.getSecretKey();



    }

}
