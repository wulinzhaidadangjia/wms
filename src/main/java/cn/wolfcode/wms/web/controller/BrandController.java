package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    @RequestPermission("品牌列表")
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) throws Exception {
        model.addAttribute("result", brandService.query(qo));
        return "/brand/list";
    }

    @RequestPermission("品牌删除")
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        brandService.delete(id);
        return new JsonResult();
    }

    @RequestPermission("品牌编辑")
    @RequestMapping("input")
    public Object list(Model model, Brand entity) {
        if (entity.getId() != null) {
            Brand dept = brandService.get(entity.getId());
            model.addAttribute("entity", dept);
        }
        return "brand/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Model model, Brand entity) {
        brandService.saveOrUpdate(entity);
        return new JsonResult();
    }
}
