package kollus.stmp.stmp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StmpApplication {

    public static void main(String[] args) {
         SpringApplication.run(StmpApplication.class, args);

        /*try (ConfigurableApplicationContext ctx = SpringApplication.run(StmpApplication.class, args)) {
            KollusConfig m = ctx.getBean(KollusConfig.class);
            m.toString();
        }*/
    }
}
