package kollus.stmp.stmp.controller;

import kollus.stmp.stmp.component.CustomListComponent;
import kollus.stmp.stmp.component.SendMailComponent;
import kollus.stmp.stmp.dao.*;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private SendMailComponent SendMailComponent;
    @Autowired
    private CustomListComponent customListComponent;
    @Autowired
    private DbReservationRepository dbReservationRepository;
    @Autowired
    private DbCustomerRepository DbCustomerRepository;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public ModelAndView customerList(Model model) {
        List<HashMap<String, String>> tb_data= customListComponent.getCustomerList();
        model.addAttribute("tbdata", tb_data);

        return new ModelAndView("Customer");
    }

    @ResponseBody
    @RequestMapping(value = {"/detailinfo"}, method = RequestMethod.POST)
    public List<DbCustomerEntity> reservationDetail(HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        String customerCode = request.getParameter("customerCode");
        List<DbCustomerEntity> tb_data = customListComponent.getDetailCustomerInfo(customerCode);

        return tb_data;
    }

}
