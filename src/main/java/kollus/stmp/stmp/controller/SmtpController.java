package kollus.stmp.stmp.controller;

import kollus.stmp.stmp.component.ScheduleComponent;
import kollus.stmp.stmp.component.SendMailComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class SmtpController {

    @Autowired
    private SendMailComponent SendMailComponent;
    @Autowired
    private ScheduleComponent ScheduleComponent;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView index() throws Exception{
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"/smtp"}, method = RequestMethod.GET)
    public ModelAndView test() throws Exception{
        System.out.println("===============inSite Spring by Jae Yoon Lee - Get Test=======================");
        System.out.println(ScheduleComponent.scheduledExecutionTime());

       // SendMailComponent.getHTMLMailForm();
       // SendMailComponent.sendMailingSystem("");
       // List<DbtestEntity> items = dbtestRepository.findByRange();
      //  System.out.println(items.toString());
      //  SendMailComponent.test123();

        return new ModelAndView("test");
    }

    @RequestMapping(value = {"/formTest"}, method = RequestMethod.POST)
    public ModelAndView formTest(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("===============inSite Spring by Jae Yoon Lee - formTest=======================");

        request.setCharacterEncoding("utf-8");

        /*Enumeration params = request.getParameterNames();
        System.out.println("----------------------------");
        while (params.hasMoreElements()){
            String name = (String)params.nextElement();
            System.out.println(name + " : " +request.getParameter(name));
        }
        System.out.println("----------------------------");*/

        //System.out.println(request.getParameter("mailForm"));
        String textBody = request.getParameter("mailForm");
        if(!textBody.isEmpty()){
           String sendMailHTML =  SendMailComponent.getHTMLMailForm(textBody);
            SendMailComponent.sendMailingSystem(sendMailHTML);
        }
        return new ModelAndView("test");
    }

}
