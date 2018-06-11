package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("login")
    @ResponseBody
    public Object login(String username, String password) {
        JsonResult jsonResult = new JsonResult();
        try {
            employeeService.login(username, password);
        } catch (Exception e) {
            String msg = e.getMessage();
            jsonResult.mark(msg);
        }
        return jsonResult;
    }

    @RequestMapping("main")
    public String main() {
        return "main";
    }

    @RequestMapping("logout")
    public String logOut(HttpServletRequest request) {

        return "redirect:/login.jsp";
    }
}
