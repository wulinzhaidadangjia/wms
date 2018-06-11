package cn.wolfcode.wms.service;

import cn.wolfcode.wms.query.OrderChartQueryObject;
import cn.wolfcode.wms.query.SaleAccountQueryObject;

import java.util.List;
import java.util.Map;


public interface IChartService {
    List<Map<String, Object>> selectOrderChart(OrderChartQueryObject qo);

    List<Map<String, Object>> selectSaleChart(SaleAccountQueryObject qo);
}
