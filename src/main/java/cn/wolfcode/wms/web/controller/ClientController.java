package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("client")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @RequestPermission("客户列表")
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) throws Exception {
        model.addAttribute("result", clientService.query(qo));
        return "/client/list";
    }

    @RequestPermission("客户删除")
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        clientService.delete(id);
        return new JsonResult();
    }

    @RequestPermission("客户编辑")
    @RequestMapping("input")
    public Object list(Model model, Client entity) {
        if (entity.getId() != null) {
            Client dept = clientService.get(entity.getId());
            model.addAttribute("entity", dept);
        }
        return "client/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Model model, Client entity) {
        clientService.saveOrUpdate(entity);
        return new JsonResult();
    }
}
