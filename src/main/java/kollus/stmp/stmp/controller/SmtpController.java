package kollus.stmp.stmp.controller;

import kollus.stmp.stmp.component.SendMailComponent;
import kollus.stmp.stmp.dao.DbGroupReservationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class SmtpController {

    @Autowired
    private SendMailComponent SendMailComponent;


    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public ModelAndView index(Model model) throws Exception{
        model.addAttribute("html", "content/sendMailpage");
        model.addAttribute("fragment", "sendmailpage");

        return new ModelAndView("layout");
    }

    @RequestMapping(value = {"/sendlist"}, method = RequestMethod.GET)
    public ModelAndView sendList(Model model) {
        List<DbGroupReservationEntity> tb_data = SendMailComponent.getEmailSendList();
        model.addAttribute("tbdata", tb_data);
        model.addAttribute("html", "content/sendList");
        model.addAttribute("fragment", "sendList");

        return new ModelAndView("layout");
    }

    @ResponseBody
    @RequestMapping(value = {"/getSendMailDetail"}, method = RequestMethod.POST)
    public HashMap<String, String> sendMailDetail( HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        String group_code = request.getParameter("groupCdoe");
        HashMap<String, String> tb_data = SendMailComponent.getSendMailDetail(group_code);

        return tb_data;
    }


/*
    @RequestMapping(value = {"/sendmailpage1"}, method = RequestMethod.GET)
    public ModelAndView mailpage() throws Exception{
        System.out.println("===============inSite Spring by Jae Yoon Lee - Get smtp=======================");

        //Add new Employee object



       // SendMailComponent.getHTMLMailForm();
       // SendMailComponent.sendMailingSystem("");
       // List<DbtestEntity> items = dbtestRepository.findByRange();
      //  System.out.println(items.toString());
      //  SendMailComponent.test123();

        *//*Enumeration params = request.getParameterNames();
        System.out.println("----------------------------");
        while (params.hasMoreElements()){
            String name = (String)params.nextElement();
            System.out.println(name + " : " +request.getParameter(name));
        }
        System.out.println("----------------------------");*//*

        return new ModelAndView("sendMailpage");
    }*/

    @ResponseBody
    @RequestMapping(value = {"/sendMail123"}, method = RequestMethod.POST)
    public HashMap<String, Object> sendMail(HttpServletRequest request) throws Exception{
        System.out.println("===============inSite Spring by Jae Yoon Lee - sendMail=======================");

        request.setCharacterEncoding("utf-8");

        HashMap<String, Object> result = new HashMap<String, Object>();
        String textBody = request.getParameter("mailForm");
        String sendType = request.getParameter("type");
        System.out.println(sendType);
        if(!textBody.isEmpty()){
            HashMap<String, String> costomerList = SendMailComponent.getCustomerCode(sendType, "");
          //  String sendMailHTML =  SendMailComponent.getHTMLMailForm(textBody);
         //   result= SendMailComponent.sendMailingSystem(sendMailHTML, costomerList);
        }else{
            result.put("error", 1);
            result.put("message", "-null에러 메일 양식이 없습니다.");
        }

        return result;
    }

}
