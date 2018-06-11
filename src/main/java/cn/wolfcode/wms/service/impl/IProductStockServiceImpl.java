package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import cn.wolfcode.wms.service.IProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IProductStockServiceImpl implements IProductStockService {
    @Autowired
    private ProductStockMapper productStockMapper;


    @Override
    public PageResult query(ProductStockQueryObject qo) {
        int totalCount = productStockMapper.query4Count(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_RESULT;
        }
        List<ProductStock> list = productStockMapper.query4List(qo);

        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, list);
    }
}

