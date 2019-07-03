package kollus.stmp.stmp.controller;

import kollus.stmp.stmp.component.CustomListComponent;
import kollus.stmp.stmp.component.ScheduleComponent;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class EmailJobSchedulerController {
    private static final Logger logger = LoggerFactory.getLogger(EmailJobSchedulerController.class);

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ScheduleComponent scheduleComponent;
    @Autowired
    private CustomListComponent customListComponent;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public ModelAndView mailpage(Model model) throws Exception{
        List<HashMap<String, String>> tb_data= customListComponent.getCustomerList();
        model.addAttribute("tbdata", tb_data);
        model.addAttribute("html", "content/sendMailpage");
        model.addAttribute("fragment", "sendmailpage");

        return new ModelAndView("layout");
    }

    @ResponseBody
    @RequestMapping(value = {"/sendMail"}, method = RequestMethod.POST)
    public HashMap<String, Object> scheduleEmail(HttpServletRequest request) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        try {
            request.setCharacterEncoding("utf-8");
            String textBody = request.getParameter("mailForm");
            String emailTitle = request.getParameter("title");
            String resDate = request.getParameter("resDate");

            LocalDateTime sendDateTime= scheduleComponent.getReservationDate(resDate);
            String groupCode = scheduleComponent.getGroupCodeForm();
            String sendBody = scheduleComponent.getHTMLMailForm(textBody);
            List<HashMap<String, String>> customerlist= customListComponent.getCustomerList();

            ZonedDateTime dateTime = ZonedDateTime.of(sendDateTime, ZoneId.of("Asia/Seoul"));

            scheduleComponent.setGroupTableData(groupCode, emailTitle, dateTime);
            scheduleComponent.setReservationTableData(customerlist, groupCode, emailTitle, sendBody);

            JobDetail jobDetail = scheduleComponent.buildJobDetail(groupCode);
            Trigger trigger = scheduleComponent.buildJobTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);

            result.put("error", 0);
            result.put("message", "메일 전송 등록에 성공했습니다.");

        } catch (SchedulerException ex) {
            logger.error("Error scheduling email", ex);
            result.put("error", 1);
            result.put("message", ex);
        }

        return result;
    }


}
