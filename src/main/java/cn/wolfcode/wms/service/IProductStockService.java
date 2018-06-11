package cn.wolfcode.wms.service;

import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductStockQueryObject;

public interface IProductStockService {

    PageResult query(ProductStockQueryObject qo);
}
