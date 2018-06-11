package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.exception.LogicException;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.StockOutcomeBillQueryObject;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IStockOutcomeBillService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("stockOutcomeBill")
public class StockOutcomeBillController {
    @Autowired
    private IStockOutcomeBillService stockOutcomeBillService;
    @Autowired
    private IDepotService depotService;

    @RequestPermission("采购单列表")
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") StockOutcomeBillQueryObject qo, Model model)
            throws Exception {
        List<Depot> depots = depotService.list();
        model.addAttribute("depots", depots);
        PageResult result = stockOutcomeBillService.query(qo);
        model.addAttribute("result", result);
        return "/stockOutcomeBill/list";
    }

    @RequestPermission("采购单编辑")
    @RequestMapping("input")
    public String input(Long id, Model model)
            throws Exception {
        List<Depot> depots = depotService.list();
        model.addAttribute("depots", depots);
        StockOutcomeBill entity = stockOutcomeBillService.get(id);
        model.addAttribute("entity", entity);
        return "/stockOutcomeBill/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(StockOutcomeBill entity, Model model)
            throws Exception {
        stockOutcomeBillService.saveOrUpdate(entity);
        return new JsonResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id, Model model)
            throws Exception {
        stockOutcomeBillService.delete(id);
        return new JsonResult();
    }

    @RequestMapping("view")
    public String view(Long id, Model model)
            throws Exception {
        List<Depot> depots = depotService.list();
        model.addAttribute("depots", depots);
        StockOutcomeBill entity = stockOutcomeBillService.get(id);
        model.addAttribute("entity", entity);
        return "/stockOutcomeBill/view";
    }

    @RequestMapping("audit")
    @ResponseBody
    public Object audit(Long id, Model model)
            throws Exception {
        JsonResult result = new JsonResult();
        try {
            stockOutcomeBillService.audit(id);
        } catch (LogicException e) {
            e.printStackTrace();
            result.mark(e.getMessage());
        }
        return result;
    }
}
