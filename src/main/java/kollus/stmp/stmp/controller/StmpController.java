package kollus.stmp.stmp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@RestController
@RequestMapping("/stmp")
public class StmpController {

    /*@RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(Locale locale, Model model){
        System.out.println("===============inSite Spring by Jae Yoon Lee - Get Test=======================");

        return "test";
    }*/

    @RequestMapping(path="", method=RequestMethod.GET)
    public ModelAndView index() {

        return new ModelAndView("test");
    }
}
