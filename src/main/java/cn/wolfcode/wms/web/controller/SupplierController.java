package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.ISupplierService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("supplier")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    @RequestPermission("供应商列表")
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) throws Exception {
        model.addAttribute("result", supplierService.query(qo));
        return "/supplier/list";
    }

    @RequestPermission("供应商删除")
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        supplierService.delete(id);
        return new JsonResult();
    }

    @RequestPermission("供应商编辑")
    @RequestMapping("input")
    public Object list(Model model, Supplier entity) {
        if (entity.getId() != null) {
            Supplier dept = supplierService.get(entity.getId());
            model.addAttribute("entity", dept);
        }
        return "supplier/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Model model, Supplier entity) {
        supplierService.saveOrUpdate(entity);
        return new JsonResult();
    }
}
