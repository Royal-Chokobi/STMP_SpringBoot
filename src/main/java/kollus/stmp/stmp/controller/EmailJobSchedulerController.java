package kollus.stmp.stmp.controller;

import kollus.stmp.stmp.component.ScheduleComponent;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class EmailJobSchedulerController {
    private static final Logger logger = LoggerFactory.getLogger(EmailJobSchedulerController.class);

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ScheduleComponent scheduleComponent;

    @RequestMapping(value = {"/test123"}, method = RequestMethod.GET)
    public String scheduleEmail() {
        try {

          //  List<Object[]> resultList = dbCustomerCodeRepository.GetCustomerEmail();

            HashMap<String, String> results = new HashMap<String, String>();
            for (Object[] borderTypes: resultList) {
                results.put((String)borderTypes[0], (String)borderTypes[1]);
                // key = customer , value = customer_email
            }

          //  ZonedDateTime dateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Seoul"));
            ZonedDateTime dateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Seoul"));
          //  System.out.println("now : "+LocalDateTime.now());

            JobDetail jobDetail = scheduleComponent.buildJobDetail();
            Trigger trigger = scheduleComponent.buildJobTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException ex) {
            logger.error("Error scheduling email", ex);
        }
        return "";
    }


}
