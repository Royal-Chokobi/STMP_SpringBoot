package kollus.stmp.stmp.controller;

import kollus.stmp.stmp.component.CustomListComponent;
import kollus.stmp.stmp.dao.DbCustomerEntity;
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
    private CustomListComponent customListComponent;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public ModelAndView customerList(Model model) {
        List<HashMap<String, String>> tb_data= customListComponent.getCustomerList();
        model.addAttribute("tbdata", tb_data);

        model.addAttribute("html", "content/customer");
        model.addAttribute("fragment", "customer");
        return new ModelAndView("layout");
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

   /* @ResponseBody
    @RequestMapping(value = {"/createcustomer"}, method = RequestMethod.POST)
    public HashMap<String, String> createCustomer(HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        String customer_name = request.getParameter("customer_name");
        String requestBody = request.getParameter("manager_info");
        System.out.println(customer_name);
        System.out.println(requestBody.toString());

        List<HashMap<String, String>> test33 = JsonPath.read(requestBody, "$.manager_info.*");
      //  HashMap<String, String> result = customListComponent.delCustomer(customerCode);
        System.out.println(customer_name);
        System.out.println(test33.toString());
        System.out.println(test33.get(0));
        //System.out.println(test33.get(0).get("manager_email"));
        return null;
    }*/

}
