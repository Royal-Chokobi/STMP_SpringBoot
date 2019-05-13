package kollus.stmp.stmp.controller;

import kollus.stmp.stmp.component.SendMailComponent;
import kollus.stmp.stmp.dao.DbGroupReservationEntity;
import kollus.stmp.stmp.dao.DbReservationEntity;
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
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private SendMailComponent SendMailComponent;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public ModelAndView reservationList(Model model) {
        List<DbGroupReservationEntity> tb_data = SendMailComponent.getEmailReservationList();
        model.addAttribute("tbdata", tb_data);
        model.addAttribute("html", "content/reservationList");
        model.addAttribute("fragment", "reservationList");

        return new ModelAndView("layout");
    }

    @ResponseBody
    @RequestMapping(value = {"/getReservationDetail"}, method = RequestMethod.POST)
    public List<DbReservationEntity> reservationDetail( HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        String group_code = request.getParameter("groupCdoe");
        List<DbReservationEntity> tb_data = SendMailComponent.getReservationDetail(group_code);

        return tb_data;
    }

    @ResponseBody
    @RequestMapping(value = {"/cancelReservation"}, method = RequestMethod.POST)
    public HashMap<String, Object> cancelReservationEmail( HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        String kind = request.getParameter("kind");
        String group_code = request.getParameter("group_code");
        String res_code = request.getParameter("res_code");

        HashMap<String, Object> result = SendMailComponent.reservationStateCancel(kind, group_code, res_code);


        return result;
    }

}
