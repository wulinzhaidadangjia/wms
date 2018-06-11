package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.query.StockIncomeBillQueryObject;

import java.util.List;

public interface StockIncomeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockIncomeBill entity);

    StockIncomeBill selectByPrimaryKey(Long id);

    List<StockIncomeBill> selectAll();

    int updateByPrimaryKey(StockIncomeBill entity);

    int query4Count(QueryObject qo);

    List<StockIncomeBill> query4List(StockIncomeBillQueryObject qo);

    void audit(StockIncomeBill oldBill);
}