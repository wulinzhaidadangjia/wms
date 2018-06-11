package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.service.IDepartmentService;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;


    @RequestPermission("员工列表")
    @RequestMapping("list")
    public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo) {
        if ("".equals(qo.getKeyword())) {

        }
        List<Department> depts = departmentService.list();
        model.addAttribute("depts", depts);
        PageResult result = employeeService.query(qo);
        model.addAttribute("result", result);
        return "employee/list";
    }

    @RequestPermission("员工编辑")
    @RequestMapping("input")
    public String list(Model model, Employee entity) {

        List<Department> depts = departmentService.list();
        List<Role> roles = roleService.list();
        model.addAttribute("depts", depts);
        model.addAttribute("roles", roles);
        if (entity.getId() != null) {
            Employee employee = employeeService.get(entity.getId());
            model.addAttribute("entity", employee);
        }
        return "employee/input";
    }

    @RequestPermission("员工批量删除")
    @RequestMapping("batchDelete")
    @ResponseBody
    public Object batchDelete(Long[] ids) {
        employeeService.batchDelete(ids);
        return new JsonResult();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Employee entity, Long[] ids) {
        employeeService.saveOrUpdate(entity, ids);
        return new JsonResult();
    }

}
