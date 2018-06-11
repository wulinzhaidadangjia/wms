package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.OrderBillItem;

import java.util.List;

public interface OrderBillItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBillItem entity);

    OrderBillItem selectByPrimaryKey(Long id);

    List<OrderBillItem> selectAll();

    int updateByPrimaryKey(OrderBillItem entity);

    void deleteByBillId(Long id);
}