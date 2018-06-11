package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.mapper.PermissionMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.util.PermissionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private ApplicationContext ctx;


    @Override
    public void delete(Long id) {
        if (id != null) {
            permissionMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public List<Permission> list() {
        return permissionMapper.selectAll();
    }

    @Override
    public void reload() {
        //1.获取数据库中所有的权限表达式
        List<String> expressions = permissionMapper.seletAllExpression();
        //2.获取容器中所有的controller
        Map<String, Object> controllers = ctx.getBeansWithAnnotation(Controller.class);
        for (Object controller : controllers.values()) {
            Method[] methods = controller.getClass().getDeclaredMethods();
            //3.迭代controller中所有的方法
            for (Method method : methods) {
                //4.生成权限表达式(类名:方法名)
                String expre = PermissionUtil.getExpression(method);
                //5.对比数据库中是否已经存在该注解,否,就添加进数据库.
                RequestPermission anno = method.getAnnotation(RequestPermission.class);
                if (anno != null) {
                    if (!expressions.contains(expre)) {
                        Permission per = new Permission();
                        per.setName(anno.value());
                        per.setExpression(expre);
                        permissionMapper.insert(per);
                    }
                }
            }
        }

    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = permissionMapper.query4Count(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_RESULT;
        }
        List<Permission> list = permissionMapper.query4List(qo);

        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, list);
    }
}

