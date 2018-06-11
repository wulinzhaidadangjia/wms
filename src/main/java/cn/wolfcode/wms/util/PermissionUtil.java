package cn.wolfcode.wms.util;

import cn.wolfcode.wms.annotation.RequestPermission;

import java.lang.reflect.Method;

public abstract class PermissionUtil {
    public static String getExpression(Method method) {
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        return className + ":" + methodName;
    }
}
