package kollus.stmp.stmp.component;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class ScheduleComponent extends TimerTask{

    public ScheduleComponent() {
    }

    public void test() throws Exception{
        Timer m_timer = new Timer(true);

        String oldstring = "2019-04-04 13:50:00";
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);
        //Date firstTime = new Date();
        System.out.println(date);
        long period = 300*1000;
        TimerTask m_task = new ScheduleComponent();
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
    }
}