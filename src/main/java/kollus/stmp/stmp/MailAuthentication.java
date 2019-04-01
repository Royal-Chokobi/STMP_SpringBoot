package kollus.stmp.stmp;

import org.springframework.stereotype.Component;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;

@Component
public class MailAuthentication extends Authenticator {
    PasswordAuthentication passAuth;

    public MailAuthentication(){ }

    public MailAuthentication(String id, String pass){
        passAuth = new PasswordAuthentication(id, pass);
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return passAuth;
    }

}
