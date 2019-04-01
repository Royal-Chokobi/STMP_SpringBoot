package kollus.stmp.stmp.controller;

import kollus.stmp.stmp.SendMailComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SmtpController {

    @Autowired
    private SendMailComponent SendMailComponent;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test() throws Exception{
        System.out.println("===============inSite Spring by Jae Yoon Lee - Get Test=======================");
       // SendMailComponent.getHTMLMailForm();
       // SendMailComponent.sendMailingSystem("");
        return "test";
    }

}
