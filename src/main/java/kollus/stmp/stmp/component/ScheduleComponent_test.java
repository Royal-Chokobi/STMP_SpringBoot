package kollus.stmp.stmp.component;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class ScheduleComponent_test implements Job{

    private SchedulerFactory schedulerFactory;
    private Scheduler scheduler;

    public ScheduleComponent_test(){
    }

    @PostConstruct
    public void start() throws SchedulerException {

        schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        //job 지정
        JobDetail job = JobBuilder.newJob(ScheduleComponent_test.class).withIdentity("testJob").build();

        //trigger 생성
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("15 * * * * ?"))
                .build();
//        startAt과 endAt을 사용해 job 스케쥴의 시작, 종료 시간도 지정할 수 있다.
//        Trigger trigger = TriggerBuilder.newTrigger().startAt(startDateTime).endAt(EndDateTime)
//                .withSchedule(CronScheduleBuilder.cronSchedule("*/1 * * * *")).build();

        scheduler.scheduleJob(job, trigger);

    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("테스트트트트");
        System.out.println(jobExecutionContext.getTrigger());
        Trigger tg = jobExecutionContext.getTrigger();
        System.out.println(tg.getKey());

    }

   /*
    public void test() throws Exception{
        Timer m_timer = new Timer(true);

        String oldstring = "2019-04-04 13:50:00";
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);
        //Date firstTime = new Date();
        System.out.println(date);
        long period = 300*1000;
        TimerTask m_task = new ScheduleComponent_test();
        m_timer.schedule(m_task,date);

    }

    @Override
    public boolean cancel() {
        return super.cancel();
    }

    @Override
    public long scheduledExecutionTime() {
        return super.scheduledExecutionTime();
    }

    @Override
    public void run() {
        System.out.println("test : "+ new Date() + " ============ " + scheduledExecutionTime()+ " ============ ");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}