package cn.wolfcode.wms.interceptor;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.exception.SecurityException;
import cn.wolfcode.wms.util.PermissionUtil;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
    //权限访问方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Employee emp = UserContext.getCurrentUser();
        //判断是否是超级管理员
        if (emp.isAdmin()) {
            return true;
        }
        //获取方法是否需要权限访问
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        RequestPermission anno = method.getDeclaredAnnotation(RequestPermission.class);
        if (anno == null) {
            return true;
        }
        //如果方法需要权限,获取该方法对应得权限表达式与该用户拥有的权限对比
        String expression = PermissionUtil.getExpression(method);
        //List<String> exps = (List<String>) request.getSession().getAttribute("EXPS_IN_SESSION");
        List<String> exps = UserContext.getExpression();
        if (exps.contains(expression)) {
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp").forward(request, response);
        throw new SecurityException();
    }
}





