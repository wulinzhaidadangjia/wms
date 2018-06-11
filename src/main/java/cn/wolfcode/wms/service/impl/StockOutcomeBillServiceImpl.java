package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.domain.StockOutcomeBillItem;
import cn.wolfcode.wms.exception.LogicException;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.mapper.StockOutcomeBillItemMapper;
import cn.wolfcode.wms.mapper.StockOutcomeBillMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.StockOutcomeBillQueryObject;
import cn.wolfcode.wms.service.IStockOutcomeBillService;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
    @Autowired
    private StockOutcomeBillMapper stockOutcomeBillMapper;
    @Autowired
    private StockOutcomeBillItemMapper stockOutcomeBillItemMapper;
    @Autowired
    private ProductStockMapper productStockMapper;

    @Override
    public void saveOrUpdate(StockOutcomeBill entity) {
        //封装订单信息
        entity.setInputUser(UserContext.getCurrentUser());
        entity.setInputTime(new Date());
        List<StockOutcomeBillItem> items = entity.getItems();
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (StockOutcomeBillItem item : items) {
            totalNumber = totalNumber.add(item.getNumber());
            //总金额
            totalAmount = totalAmount.add(totalNumber.multiply(item.getSalePrice()));
        }
        //封装总金额和总数量到数据库
        entity.setTotalAmount(totalAmount);
        entity.setTotalNumber(totalNumber);
        if (entity.getId() == null) {
            stockOutcomeBillMapper.insert(entity);
        } else {
            stockOutcomeBillMapper.updateByPrimaryKey(entity);
            //删除之前所有的明细
            stockOutcomeBillItemMapper.deleteByBillId(entity.getId());
        }
        //保存新的明细
        for (StockOutcomeBillItem item : items) {
            item.setBill_id(entity.getId());
            item.setAmount(item.getNumber().multiply(item.getSalePrice()));
            stockOutcomeBillItemMapper.insert(item);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            stockOutcomeBillMapper.deleteByPrimaryKey(id);
            //删除明细
            stockOutcomeBillItemMapper.deleteByBillId(id);
        }
    }

    @Override
    public void audit(Long id) {
        StockOutcomeBill oldBill = stockOutcomeBillMapper.selectByPrimaryKey(id);
        List<StockOutcomeBillItem> items = oldBill.getItems();
        Long depotId = oldBill.getDepot().getId();
        //遍历items,判断是否已经有商品存在数据库中
        for (StockOutcomeBillItem item : items) {
            Long productId = item.getProduct().getId();
            ProductStock ps = productStockMapper.selectByPIdAndDepotId(productId, depotId);
            if (ps == null) {
                throw new LogicException("商品[" + item.getProduct().getName() + "]在[" + oldBill.getDepot().getName() + "]中不存在");
            } else if (ps.getStoreNumber().compareTo(item.getNumber()) < 0) {
                throw new LogicException("商品[" + item.getProduct().getName() + "]库存量[" + ps.getStoreNumber() + "]不足");
            }

            //存在,修改库存量,价格
            ps.setStoreNumber(item.getNumber().subtract(ps.getStoreNumber()));
            ps.setAmount(ps.getPrice().multiply(ps.getStoreNumber()).setScale(BigDecimal.ROUND_HALF_DOWN));
            productStockMapper.updateByPrimaryKey(ps);
        }
        if (oldBill.getStatus() == StockOutcomeBill.STATUS_NOMAL) {
            oldBill.setStatus(StockOutcomeBill.STATUS_AUDIT);
            oldBill.setAuditor(UserContext.getCurrentUser());
            oldBill.setAuditTime(new Date());
            stockOutcomeBillMapper.audit(oldBill);
        }
    }

    @Override
    public StockOutcomeBill get(Long id) {
        return stockOutcomeBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StockOutcomeBill> list() {
        return stockOutcomeBillMapper.selectAll();
    }

    @Override
    public PageResult query(StockOutcomeBillQueryObject qo) {
        int totalCount = stockOutcomeBillMapper.query4Count(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_RESULT;
        }
        List<StockOutcomeBill> list = stockOutcomeBillMapper.query4List(qo);

        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, list);
    }
}

