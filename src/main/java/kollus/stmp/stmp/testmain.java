package kollus.stmp.stmp;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Component
public class testmain {

    public static void main(String[] args) throws Exception{
        UUID uuid = UUID.randomUUID();
        //String jobKeyName = Long.toString(uuid.getMostSignificantBits(), 36) + Long.toString(uuid.getLeastSignificantBits(), 36);
        String jobKeyName = Long.toString(uuid.getLeastSignificantBits(), 36);
        System.out.println(jobKeyName);
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().plusMinutes(5));

        Date from = new Date();

        SimpleDateFormat transFormat = new SimpleDateFormat("yyMMddHHmmss");

        String to = transFormat.format(from);
        System.out.println(LocalDateTime.now().toString());
        /*Scheduler scheduler = new StdSchedulerFactory().getScheduler();
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
        scheduler.shutdown();*/
        //https://www.mkyong.com/java/quartz-2-scheduler-tutorial/ 참고


    }
}