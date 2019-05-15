package kollus.stmp.stmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmtpApplication {

    public static void main(String[] args)  throws Exception{
        SpringApplication.run(SmtpApplication.class, args);

        //ScheduleComponent scCom= new ScheduleComponent();
        //scCom.test();
       /* try (ConfigurableApplicationContext ctx = SpringApplication.run(SmtpApplication.class, args)) {
            ScheduleComponent m = ctx.getBean(ScheduleComponent.class);
            m.test();
        }*/
    }
}
