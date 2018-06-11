package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @RequestPermission("部门列表")
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) throws Exception {
        model.addAttribute("result", departmentService.query(qo));
        return "/department/list";
    }

    @RequestPermission("部门删除")
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        departmentService.delete(id);
        return new JsonResult();
    }

    @RequestPermission("部门编辑")
    @RequestMapping("input")
    public Object list(Model model, Department entity) {
        if (entity.getId() != null) {
            Department dept = departmentService.get(entity.getId());
            model.addAttribute("entity", dept);
        }
        return "department/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Model model, Department entity) {
        departmentService.saveOrUpdate(entity);
        return new JsonResult();
    }
}
