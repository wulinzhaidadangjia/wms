package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestPermission("权限列表")
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) throws Exception {
        model.addAttribute("result", permissionService.query(qo));
        return "/permission/list";
    }

    @RequestPermission("权限删除")
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        permissionService.delete(id);
        return new JsonResult();
    }

    @RequestPermission("权限加载")
    @RequestMapping("reload")
    @ResponseBody
    public Object reload() {
        permissionService.reload();
        return new JsonResult();
    }
}
