package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.domain.OrderBillItem;
import cn.wolfcode.wms.mapper.OrderBillItemMapper;
import cn.wolfcode.wms.mapper.OrderBillMapper;
import cn.wolfcode.wms.query.OrderBillQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.service.IOrderBillService;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderBillServiceImpl implements IOrderBillService {
    @Autowired
    private OrderBillMapper orderBillMapper;
    @Autowired
    private OrderBillItemMapper orderBillItemMapper;

    @Override
    public void saveOrUpdate(OrderBill entity) {
        //封装订单信息
        entity.setInputUser(UserContext.getCurrentUser());
        entity.setInputTime(new Date());
        List<OrderBillItem> items = entity.getItems();
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (OrderBillItem item : items) {
            totalNumber = totalNumber.add(item.getNumber());
            //总金额
            totalAmount = totalAmount.add(totalNumber.multiply(item.getCostPrice()));
        }
        //封装总金额和总数量到数据库
        entity.setTotalAmount(totalAmount);
        entity.setTotalNumber(totalNumber);
        if (entity.getId() == null) {
            orderBillMapper.insert(entity);
        } else {
            orderBillMapper.updateByPrimaryKey(entity);
            //删除之前所有的明细
            orderBillItemMapper.deleteByBillId(entity.getId());
        }
        //保存新的明细
        for (OrderBillItem item : items) {
            item.setBill_id(entity.getId());
            item.setAmount(item.getNumber().multiply(item.getCostPrice()));
            orderBillItemMapper.insert(item);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            orderBillMapper.deleteByPrimaryKey(id);
            //删除明细
            orderBillItemMapper.deleteByBillId(id);
        }
    }

    @Override
    public void audit(Long id) {
        //1.获取采购订单数据
        OrderBill oldBill = orderBillMapper.selectByPrimaryKey(id);
        //2.判断采购订单的审核状态信息,
        if (oldBill.getStatus() == OrderBill.STATUS_NOMAL) {
            oldBill.setStatus(OrderBill.STATUS_AUDIT);
            //设置审核人的信息和审核信息
            oldBill.setAuditor(UserContext.getCurrentUser());
            oldBill.setAuditTime(new Date());
            orderBillMapper.audit(oldBill);
        }
    }

    @Override
    public OrderBill get(Long id) {
        return orderBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderBill> list() {
        return orderBillMapper.selectAll();
    }

    @Override
    public PageResult query(OrderBillQueryObject qo) {
        int totalCount = orderBillMapper.query4Count(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_RESULT;
        }
        List<OrderBill> list = orderBillMapper.query4List(qo);

        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, list);
    }
}

