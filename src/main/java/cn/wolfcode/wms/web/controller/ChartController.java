package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.query.OrderChartQueryObject;
import cn.wolfcode.wms.query.SaleAccountQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IChartService;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.service.ISupplierService;
import cn.wolfcode.wms.util.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("chart")
public class ChartController {
    @Autowired
    private IChartService chartServicer;
    @Autowired
    private IBrandService brandServicer;
    @Autowired
    private ISupplierService supplierServicer;
    @Autowired
    private IClientService clientServicer;

    @RequestMapping("order")
    public String chart(Model model, @ModelAttribute("qo") OrderChartQueryObject qo) {
        List<Brand> brands = brandServicer.list();
        model.addAttribute("brands", brands);
        List<Supplier> suppliers = supplierServicer.list();
        model.addAttribute("suppliers", suppliers);
        List<Map<String, Object>> orderCharts = chartServicer.selectOrderChart(qo);
        model.addAttribute("orderCharts", orderCharts);
        //将map中的key值作为响应参数传给前端,对应页面回显map的value值,
        //页面查询请求,将key值作为高级查询的name的属性值传给后台.类似于表的列名.
        model.addAttribute("groupByTypeMap", OrderChartQueryObject.groupByTypeMap);
        return "chart/order";
    }

    @RequestMapping("sale")
    public String sale(Model model, @ModelAttribute("qo") SaleAccountQueryObject qo) {
        List<Brand> brands = brandServicer.list();
        model.addAttribute("brands", brands);
        List<Client> clients = clientServicer.list();
        model.addAttribute("clients", clients);
        List<Map<String, Object>> saleCharts = chartServicer.selectSaleChart(qo);
        model.addAttribute("saleCharts", saleCharts);
        //将map中的key值作为响应参数传给前端,对应页面回显map的value值,
        //页面查询请求,将key值作为高级查询的name的属性值传给后台.类似于表的列名.
        model.addAttribute("groupByTypeMap", SaleAccountQueryObject.groupByTypeMap);
        return "chart/sale";
    }

    @RequestMapping("saleByBar")
    public String saleByBar(Model model, @ModelAttribute("qo") SaleAccountQueryObject qo) throws Exception {
        List<Map<String, Object>> saleCharts = chartServicer.selectSaleChart(qo);
        List<String> groupByType = new ArrayList<>();
        List<Object> totalAmount = new ArrayList<>();
        for (Map<String, Object> chart : saleCharts) {
            String val1 = chart.get("groupType").toString();
            groupByType.add(chart.get("groupType").toString());
            totalAmount.add(chart.get("totalAmount").toString());
        }
        ObjectMapper json = new ObjectMapper();

        model.addAttribute("groupByType", JSONUtil.toJsonString(groupByType));
        model.addAttribute("totalAmount", JSONUtil.toJsonString(totalAmount));
        return "chart/saleByBar";
    }

    @RequestMapping("saleByPie")
    public String saleByPie(Model model, @ModelAttribute("qo") SaleAccountQueryObject qo) throws Exception {
        List<Map<String, Object>> saleCharts = chartServicer.selectSaleChart(qo);
        List<String> groupByType = new ArrayList<>();
        List<Map<String, Object>> datas = new ArrayList<>();
        for (Map<String, Object> chart : saleCharts) {
            groupByType.add((String) chart.get("groupType"));
            Map<String, Object> data = new HashMap<>();
            data.put("value", chart.get("totalAmount"));
            data.put("name", chart.get("groupType"));
            datas.add(data);
        }
        model.addAttribute("groupByType", JSONUtil.toJsonString(groupByType));
        model.addAttribute("totalAmount", JSONUtil.toJsonString(datas));
        return "chart/saleByPie";
    }

}
