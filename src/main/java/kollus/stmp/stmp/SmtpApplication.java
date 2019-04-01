package kollus.stmp.stmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmtpApplication {

    public static void main(String[] args) {
         SpringApplication.run(SmtpApplication.class, args);

        /*try (ConfigurableApplicationContext ctx = SpringApplication.run(StmpApplication.class, args)) {
            KollusConfig m = ctx.getBean(KollusConfig.class);
            m.toString();
        }*/
    }
}
