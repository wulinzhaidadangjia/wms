package cn.wolfcode.wms.interceptor;

import cn.wolfcode.wms.domain.Employee;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee emp = (Employee) request.getSession().getAttribute("EMP_IN_SESSION");
        if (emp == null) {
            response.sendRedirect("/login.jsp");
            return false;
        } else {
            return true;
        }

    }
}
