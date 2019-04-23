package kollus.stmp.stmp.controller;

import kollus.stmp.stmp.component.EmailJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class EmailJobSchedulerController {
    private static final Logger logger = LoggerFactory.getLogger(EmailJobSchedulerController.class);

    @Autowired
    private Scheduler scheduler;

    @RequestMapping(value = {"/test123"}, method = RequestMethod.GET)
    public String scheduleEmail() {
        try {
          //  ZonedDateTime dateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Seoul"));
            ZonedDateTime dateTime = ZonedDateTime.of(LocalDateTime.now().plusMinutes(1), ZoneId.of("Asia/Seoul"));
            System.out.println("now : "+LocalDateTime.now());

            JobDetail jobDetail = buildJobDetail();
            Trigger trigger = buildJobTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException ex) {
            logger.error("Error scheduling email", ex);
        }
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return "";
    }

    private JobDetail buildJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("email", "jaeyoon.lee@catenoid.net,ollehing@gmail.com,se@catenoid.net,sw.na@catenoid.net");
        jobDataMap.put("subject", "예약 2건 메일 테스트입니다.");
        jobDataMap.put("body", "이메일은 1분 후 전송됩니다. 2개 날아가야함./ 예약 접수 시간 now : "+LocalDateTime.now());

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}
