package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("productStock")
public class ProductStockController {
    @Autowired
    private IProductStockService productStockService;
    @Autowired
    private IDepotService depotService;
    @Autowired
    private IBrandService brandService;

    @RequestPermission("商品列表")
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") ProductStockQueryObject qo, Model model) throws Exception {
        List<Depot> depots = depotService.list();
        model.addAttribute("depots", depots);
        List<Brand> brands = brandService.list();
        model.addAttribute("brands", brands);
        PageResult result = productStockService.query(qo);
        model.addAttribute("result", result);
        return "/productStock/list";
    }
}
