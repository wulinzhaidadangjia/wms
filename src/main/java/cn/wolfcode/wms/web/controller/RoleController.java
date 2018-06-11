package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private ISystemMenuService systemMenuService;


    @RequestPermission("角色列表")
    @RequestMapping("list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        PageResult result = roleService.query(qo);
        model.addAttribute("result", result);
        return "role/list";
    }

    @RequestPermission("角色删除")
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        roleService.delete(id);
        return new JsonResult();
    }

    @RequestPermission("角色编辑")
    @RequestMapping("input")
    public String list(Model model, Role entity) {
        List<SystemMenu> menus = systemMenuService.selectAllMenu();
        model.addAttribute("menus", menus);
        List<Permission> permissions = permissionService.list();
        model.addAttribute("permissions", permissions);
        if (entity.getId() != null) {
            Role role = roleService.get(entity.getId());
            model.addAttribute("entity", role);
        }
        return "role/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Role entity, Long[] ids, Long[] mids) {
        roleService.saveOrUpdate(entity, ids, mids);
        return new JsonResult();
    }
}
