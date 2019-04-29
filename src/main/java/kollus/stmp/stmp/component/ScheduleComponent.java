package kollus.stmp.stmp.component;

import kollus.stmp.stmp.dao.DbReservationEntity;
import kollus.stmp.stmp.dao.DbReservationRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Component
public class ScheduleComponent{

    @Autowired
    private DbReservationRepository dbReservationRepository;

    public String getHTMLMailForm(String textBody){

        String mailForm = "<div style = 'width: 900px;'>";
        mailForm+=textBody;
        mailForm+="</div>";

        return mailForm;
    }

    public String getGroupCodeForm(){
        return dbReservationRepository.getGroupCode();
    }

    public JobDetail buildJobDetail(String email, String subject, String body, String groupCode) {

        UUID uuid = UUID.randomUUID();
        //String jobKeyName = Long.toString(uuid.getMostSignificantBits(), 36) + Long.toString(uuid.getLeastSignificantBits(), 36);
        String jobKeyName = Long.toString(uuid.getLeastSignificantBits(), 36).replace('-', 'C');

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("email", email);
        jobDataMap.put("subject", subject);
        jobDataMap.put("body", body);
        jobDataMap.put("resCode", jobKeyName);

        DbReservationEntity dbReservationEntity = new DbReservationEntity();
        dbReservationEntity.setEmail_title(subject);
        dbReservationEntity.setCustomer_address(email);
        dbReservationEntity.setEmail_form(body);
        dbReservationEntity.setGroup_code(groupCode);
        dbReservationEntity.setReservation_code(jobKeyName);
        dbReservationEntity.setState("R");

        dbReservationRepository.save(dbReservationEntity);

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(jobKeyName, "email-jobs")
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