package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.StockIncomeBillQueryObject;

import java.util.List;

public interface IStockIncomeBillService {

    void saveOrUpdate(StockIncomeBill entity);

    void delete(Long id);

    StockIncomeBill get(Long id);

    List<StockIncomeBill> list();

    PageResult query(StockIncomeBillQueryObject qo);

    void audit(Long id);


}
