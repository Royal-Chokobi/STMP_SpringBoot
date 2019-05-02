package kollus.stmp.stmp.component;

import kollus.stmp.stmp.dao.DbGroupReservationRepository;
import kollus.stmp.stmp.dao.DbReservationEntity;
import kollus.stmp.stmp.dao.DbReservationRepository;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class EmailJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(EmailJob.class);

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private DbReservationRepository dbReservationRepository;
    @Autowired
    private DbGroupReservationRepository dbGroupReservationRepository;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());

        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String groupCode = jobDataMap.getString("groupCode");
        sendMail(mailProperties.getUsername(), groupCode);
    }

    private void sendMail(String fromEmail, String groupCode) {
        try {
            List<DbReservationEntity> sendData = dbReservationRepository.getScheduleSendList(groupCode);

            for (DbReservationEntity item : sendData){
                logger.info("Sending Email to {}", item.getCustomer_address());

                MimeMessage message = mailSender.createMimeMessage();

                MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
                messageHelper.setSubject(item.getEmail_title());
                messageHelper.setText(item.getEmail_form(), true);
                messageHelper.setFrom(fromEmail);
                messageHelper.setTo(InternetAddress.parse(item.getCustomer_address()));
                //messageHelper.setTo(toEmail);
                mailSender.send(message);

                dbReservationRepository.updateReservationData(item.getReservation_code(),"Y");
                System.out.println("send time : "+ LocalDateTime.now());
            }
            dbGroupReservationRepository.updateGroupReservationData(groupCode,"Y");


        } catch (MessagingException ex) {
            dbGroupReservationRepository.updateGroupReservationData(groupCode,"E");
            logger.error("Failed to send email : "+groupCode);
        }

    }
}
