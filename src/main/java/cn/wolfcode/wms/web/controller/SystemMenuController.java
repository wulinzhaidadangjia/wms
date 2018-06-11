package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.SystemMenuOueryObject;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("systemMenu")
public class SystemMenuController {
    @Autowired
    private ISystemMenuService systemMenuService;

    @RequestMapping("list")
    public String list(Model model, @ModelAttribute("qo") SystemMenuOueryObject qo) throws Exception {
       //获取当前子菜单的所有父菜单,作为菜单索引或者定位当前菜单的目录位置.
        if (qo.getParentId() != null) {
            SystemMenu parent = systemMenuService.get(qo.getParentId());
            List<SystemMenu> parents = systemMenuService.getParentMenus(parent);
            model.addAttribute("parents", parents);
        }

        model.addAttribute("list", systemMenuService.list(qo));
        return "/systemMenu/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        systemMenuService.delete(id);
        return new JsonResult();
    }

    @RequestMapping("loadMenus")
    @ResponseBody
    public Object loadMenus(Model model, String parentSn) {
        List<Map<String, Object>> menus = systemMenuService.loadMenus(parentSn);
        model.addAttribute("menus", menus);
        return menus;
    }

    @RequestMapping("input")
    public Object list(Model model, Long id, Long parentId) {
        if (parentId != null) {
            SystemMenu parent = systemMenuService.get(parentId);
            model.addAttribute("parent", parent);
        }
        if (id != null) {
            SystemMenu sm = systemMenuService.get(id);
            model.addAttribute("entity", sm);
        }
        return "systemMenu/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(SystemMenu entity) {
        systemMenuService.saveOrUpdate(entity);
        return new JsonResult();
    }
}
