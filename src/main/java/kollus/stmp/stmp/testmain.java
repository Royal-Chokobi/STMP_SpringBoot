package kollus.stmp.stmp;

import kollus.stmp.stmp.component.ScheduleComponent_test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class testmain {

    public static void main(String[] args) throws Exception{

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();

        JobDetail job = JobBuilder.newJob(ScheduleComponent_test.class)
                .withIdentity("test", "group1").build();

        //CronTrigger trigger = new CronTrigger();
        //trigger.setName("test1111111");
        //trigger.setCronExpression("0/5 * * * * ?");

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("test123", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        //schedule it

        scheduler.scheduleJob(job, trigger);
        System.out.println("aaaaaaaaaa");
        scheduler.shutdown();
        //https://www.mkyong.com/java/quartz-2-scheduler-tutorial/ 참고


    }
}