package kollus.stmp.stmp;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Component
public class testmain {

    public static void main(String[] args) throws Exception{

        Date from = new Date();
        System.out.println(from);
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String to = transFormat.format(from);
        System.out.println();

        LocalDateTime  targetDateTime = null;

        //직접 변경

        targetDateTime = targetDateTime.now()
                .withDayOfMonth(02)
                .withHour(14)
                .withMinute(00)
                .withSecond(00);

        System.out.println("직접 변경: " + targetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


        System.out.println(LocalDateTime.now().withHour(3));


     //   System.out.println(java.sql.Date.valueOf(targetDateTime.toString()));


        SimpleDateFormat test1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //test1.parse(targetDateTime.toString());


        ZonedDateTime dateTime = ZonedDateTime.of(targetDateTime, ZoneId.of("Asia/Seoul"));
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Date aaa = new Date();
        dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        aaa= test1.parse(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(aaa);




        UUID uuid = UUID.randomUUID();
        //String jobKeyName = Long.toString(uuid.getMostSignificantBits(), 36) + Long.toString(uuid.getLeastSignificantBits(), 36);
        String jobKeyName = Long.toString(uuid.getLeastSignificantBits(), 36);
        System.out.println(jobKeyName);
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().plusMinutes(5));

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