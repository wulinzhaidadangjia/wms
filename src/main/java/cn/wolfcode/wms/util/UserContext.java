package cn.wolfcode.wms.util;

import cn.wolfcode.wms.domain.Employee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/*
    将登录的用户数据和权限数据使用session传递.
 */
public abstract class UserContext {
    public static final String USER_KEY = "EMP_IN_SESSION";
    public static final String EXPS_KEY = "EXPS_IN_SESSION";

    /**
     * 获取session对象
     * null
     */
    public static HttpSession getSession() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        return request.getSession();
    }

    /**
     * 获取session中USER_KEY属性.
     *
     * @Employee
     */
    public static Employee getCurrentUser() {

        return (Employee) getSession().getAttribute(USER_KEY);
    }

    /**
     * 将登录的用户信息使用session传递
     *
     * @param emp
     */
    public static void setCurrentUser(Employee emp) {
        getSession().setAttribute(USER_KEY, emp);
    }

    /**
     * 获取当前登录用户的所有权限数据
     *
     * @return 权限集合
     */
    public static List<String> getExpression() {
        return (List<String>) getSession().getAttribute(EXPS_KEY);
    }

    /**
     * 将登录的用户权限数据使用session传递
     *
     * @param expres
     */
    public static void setExpression(List<String> expres) {
        getSession().setAttribute(EXPS_KEY, expres);
    }
}
