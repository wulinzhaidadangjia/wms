package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.mapper.ChartMapper;
import cn.wolfcode.wms.query.OrderChartQueryObject;
import cn.wolfcode.wms.query.SaleAccountQueryObject;
import cn.wolfcode.wms.service.IChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChartServiceImpl implements IChartService {
    @Autowired
    private ChartMapper chartMapper;

    @Override
    public List<Map<String, Object>> selectOrderChart(OrderChartQueryObject qo) {
        return chartMapper.selectOrderChart(qo);
    }

    @Override
    public List<Map<String, Object>> selectSaleChart(SaleAccountQueryObject qo) {
        return chartMapper.selectSaleChart(qo);
    }
}

