package kollus.stmp.stmp;

import kollus.stmp.stmp.component.ScheduleComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SmtpApplication {

    public static void main(String[] args)  throws Exception{
        SpringApplication.run(SmtpApplication.class, args);

        ScheduleComponent scCom= new ScheduleComponent();
        scCom.test();
       /* try (ConfigurableApplicationContext ctx = SpringApplication.run(SmtpApplication.class, args)) {
            ScheduleComponent m = ctx.getBean(ScheduleComponent.class);
            m.test();
        }*/
    }
}
