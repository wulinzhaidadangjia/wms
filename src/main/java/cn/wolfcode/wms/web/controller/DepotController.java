package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("depot")
public class DepotController {
    @Autowired
    private IDepotService depotService;

    @RequestPermission("仓库列表")
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) throws Exception {
        model.addAttribute("result", depotService.query(qo));
        return "/depot/list";
    }

    @RequestPermission("仓库删除")
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        depotService.delete(id);
        return new JsonResult();
    }

    @RequestPermission("仓库编辑")
    @RequestMapping("input")
    public Object list(Model model, Depot entity) {
        if (entity.getId() != null) {
            Depot dept = depotService.get(entity.getId());
            model.addAttribute("entity", dept);
        }
        return "depot/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Model model, Depot entity) {
        depotService.saveOrUpdate(entity);
        return new JsonResult();
    }
}
