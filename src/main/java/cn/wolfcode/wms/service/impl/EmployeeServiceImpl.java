package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.mapper.EmployeeMapper;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.util.MD5;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private ApplicationContext ctx;

    @Override
    public void batchDelete(Long[] ids) {
        employeeMapper.batchDelete(ids);
        for (Long id : ids) {
            employeeMapper.deleteRelation(id);
        }

    }

    @Override
    public void login(String username, String password) {
        Employee employee = employeeMapper.checkLogin(username, MD5.encoder(password));
        if (employee == null) {
            throw new RuntimeException("对不起,账号密码错误");
        }
        List<String> expres = employeeMapper.getExpression(employee.getId());
        //登录同时,将用户数据和权限信息使用session传递.
        UserContext.setCurrentUser(employee);
        UserContext.setExpression(expres);
    }

    @Override
    public void saveOrUpdate(Employee entity, Long[] ids) {
        if (entity.getId() != null) {
            employeeMapper.deleteRelation(entity.getId());
            employeeMapper.updateByPrimaryKey(entity);
        } else {
            entity.setPassword(MD5.encoder(entity.getPassword()));
            employeeMapper.insert(entity);
        }
        if (ids != null && !"".equals(ids)) {
            for (Long id : ids) {
                employeeMapper.insertRelation(entity.getId(), id);
            }
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            employeeMapper.deleteByPrimaryKey(id);
            employeeMapper.deleteRelation(id);
        }
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> list() {
        return employeeMapper.selectAll();
    }

    @Override
    public PageResult query(EmployeeQueryObject qo) {
        int totalCount = employeeMapper.query4Count(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_RESULT;
        }
        List<Employee> list = employeeMapper.query4List(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, list);
    }
}
