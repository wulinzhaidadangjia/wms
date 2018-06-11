package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.query.OrderBillQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.service.IOrderBillService;
import cn.wolfcode.wms.service.ISupplierService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("orderBill")
public class OrderBillController {
    @Autowired
    private IOrderBillService orderBillService;
    @Autowired
    private ISupplierService supplierService;

    @RequestPermission("订单列表")
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") OrderBillQueryObject qo, Model model)
            throws Exception {
        List<Supplier> suppliers = supplierService.list();
        model.addAttribute("suppliers", suppliers);
        PageResult result = orderBillService.query(qo);
        model.addAttribute("result", result);
        return "/orderBill/list";
    }

    @RequestPermission("订单删除")
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        orderBillService.delete(id);

        return new JsonResult();
    }

    @RequestPermission("订单编辑")
    @RequestMapping("input")
    public Object list(Model model, OrderBill entity) {
        List<Supplier> suppliers = supplierService.list();
        model.addAttribute("suppliers", suppliers);
        if (entity.getId() != null) {
            OrderBill ob = orderBillService.get(entity.getId());
            model.addAttribute("entity", ob);
        }
        return "orderBill/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Model model, OrderBill entity) {
        JsonResult result = new JsonResult();
        try {
            orderBillService.saveOrUpdate(entity);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("对不起,您操作有误!");
        }
        return new JsonResult();
    }

    @RequestMapping("audit")
    @ResponseBody
    public Object audit(Long id) {
        orderBillService.audit(id);
        return new JsonResult();
    }

    @RequestMapping("view")
    public Object view(Long id, Model model) {
        List<Supplier> suppliers = supplierService.list();
        model.addAttribute("suppliers", suppliers);
        OrderBill entity = orderBillService.get(id);
        model.addAttribute("entity", entity);
        return "orderBill/view";
    }
}
