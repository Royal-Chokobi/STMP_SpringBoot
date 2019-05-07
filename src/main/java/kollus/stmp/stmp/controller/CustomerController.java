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
    public List<DbCustomerEntity> customerDetailInfo(HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        String customerCode = request.getParameter("customerCode");
        List<DbCustomerEntity> tb_data = customListComponent.getDetailCustomerInfo(customerCode);

        return tb_data;
    }

    @ResponseBody
    @RequestMapping(value = {"/editname"}, method = RequestMethod.POST)
    public HashMap<String, String> editCustomerName(HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        String customerCode = request.getParameter("customerCode");
        String customerNM = request.getParameter("customerNM");
        HashMap<String, String> result = customListComponent.editCustomerNM(customerNM, customerCode);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = {"/editcusinfo"}, method = RequestMethod.POST)
    public HashMap<String, String> editCustomerInfomation(HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        String index = request.getParameter("index");
        String customerNM = request.getParameter("cus_name");
        String customerEmail = request.getParameter("cus_email");
        HashMap<String, String> result = customListComponent.editCustomerInfo(index, customerNM, customerEmail);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = {"/delcusinfo"}, method = RequestMethod.POST)
    public HashMap<String, String> deleteCustomerInfomation(HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        String index = request.getParameter("index");
        HashMap<String, String> result = customListComponent.delCustomerInfo(index);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = {"/delcustomer"}, method = RequestMethod.POST)
    public HashMap<String, String> deleteCustomer(HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        String customerCode = request.getParameter("customerCode");
        HashMap<String, String> result = customListComponent.delCustomer(customerCode);

        return result;
    }

}
