package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.StockIncomeBillQueryObject;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IStockIncomeBillService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("stockIncomeBill")
public class StockIncomeBillController {
    @Autowired
    private IStockIncomeBillService stockIncomeBillService;
    @Autowired
    private IDepotService depotService;

    @RequestPermission("采购单列表")
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") StockIncomeBillQueryObject qo, Model model)
            throws Exception {
        List<Depot> depots = depotService.list();
        model.addAttribute("depots", depots);
        PageResult result = stockIncomeBillService.query(qo);
        model.addAttribute("result", result);
        return "/stockIncomeBill/list";
    }

    @RequestPermission("采购单编辑")
    @RequestMapping("input")
    public String input(Long id, Model model)
            throws Exception {
        List<Depot> depots = depotService.list();
        model.addAttribute("depots", depots);
        StockIncomeBill entity = stockIncomeBillService.get(id);
        model.addAttribute("entity", entity);
        return "/stockIncomeBill/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(StockIncomeBill entity, Model model)
            throws Exception {
        stockIncomeBillService.saveOrUpdate(entity);
        return new JsonResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id, Model model)
            throws Exception {
        stockIncomeBillService.delete(id);
        return new JsonResult();
    }

    @RequestMapping("view")
    public String view(Long id, Model model)
            throws Exception {
        List<Depot> depots = depotService.list();
        model.addAttribute("depots", depots);
        StockIncomeBill entity = stockIncomeBillService.get(id);
        model.addAttribute("entity", entity);
        return "/stockIncomeBill/view";
    }

    @RequestMapping("audit")
    @ResponseBody
    public Object audit(Long id, Model model)
            throws Exception {
        stockIncomeBillService.audit(id);
        return new JsonResult();
    }
}
