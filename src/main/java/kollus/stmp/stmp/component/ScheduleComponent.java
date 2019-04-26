package kollus.stmp.stmp.component;

import kollus.stmp.stmp.KollusConfig;
import kollus.stmp.stmp.dao.*;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

@Component
public class ScheduleComponent{

    @Autowired
    private DbCustomerCodeRepository dbCustomerCodeRepository;

    public JobDetail buildJobDetail() {

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("email", "jaeyoon.lee@catenoid.net,ollehing@gmail.com,se@catenoid.net,sw.na@catenoid.net");
        jobDataMap.put("subject", "예약 2건 메일 테스트입니다.");
        jobDataMap.put("body", "이메일은 1분 후 전송됩니다. 2개 날아가야함./ 예약 접수 시간 now : ");

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    public Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }

}