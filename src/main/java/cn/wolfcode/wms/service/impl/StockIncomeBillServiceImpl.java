package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.domain.StockIncomeBillItem;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.mapper.StockIncomeBillItemMapper;
import cn.wolfcode.wms.mapper.StockIncomeBillMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.StockIncomeBillQueryObject;
import cn.wolfcode.wms.service.IStockIncomeBillService;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
    @Autowired
    private StockIncomeBillMapper stockIncomeBillMapper;
    @Autowired
    private StockIncomeBillItemMapper stockIncomeBillItemMapper;
    @Autowired
    private ProductStockMapper productStockMapper;

    @Override
    public void saveOrUpdate(StockIncomeBill entity) {
        //封装订单信息
        entity.setInputUser(UserContext.getCurrentUser());
        entity.setInputTime(new Date());
        List<StockIncomeBillItem> items = entity.getItems();
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (StockIncomeBillItem item : items) {
            totalNumber = totalNumber.add(item.getNumber());
            //总金额
            totalAmount = totalAmount.add(totalNumber.multiply(item.getCostPrice()));
        }
        //封装总金额和总数量到数据库
        entity.setTotalAmount(totalAmount);
        entity.setTotalNumber(totalNumber);
        if (entity.getId() == null) {
            stockIncomeBillMapper.insert(entity);
        } else {
            stockIncomeBillMapper.updateByPrimaryKey(entity);
            //删除之前所有的明细
            stockIncomeBillItemMapper.deleteByBillId(entity.getId());
        }
        //保存新的明细
        for (StockIncomeBillItem item : items) {
            item.setBill_id(entity.getId());
            item.setAmount(item.getNumber().multiply(item.getCostPrice()));
            stockIncomeBillItemMapper.insert(item);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            stockIncomeBillMapper.deleteByPrimaryKey(id);
            //删除明细
            stockIncomeBillItemMapper.deleteByBillId(id);
        }
    }

    @Override
    public void audit(Long id) {
        StockIncomeBill oldBill = stockIncomeBillMapper.selectByPrimaryKey(id);
        List<StockIncomeBillItem> items = oldBill.getItems();
        Long depotId = oldBill.getDepot().getId();
        //遍历items,判断是否已经有商品存在数据库中
        for (StockIncomeBillItem item : items) {
            Long productId = item.getProduct().getId();
            ProductStock ps = productStockMapper.selectByPIdAndDepotId(productId, depotId);
            if (ps == null) {
                ps = new ProductStock();
                ps.setDepot(oldBill.getDepot());
                ps.setProduct(item.getProduct());
                ps.setAmount(item.getAmount());
                ps.setPrice(item.getCostPrice());
                ps.setStoreNumber(item.getNumber());
                productStockMapper.insert(ps);
            } else {
                //存在,修改库存量,价格
                ps.setStoreNumber(item.getNumber().add(ps.getStoreNumber()));
                ps.setPrice(item.getAmount().add(ps.getAmount()).divide(ps.getStoreNumber(), 2, BigDecimal.ROUND_HALF_UP));
                ps.setAmount(ps.getPrice().multiply(ps.getStoreNumber()).setScale(BigDecimal.ROUND_HALF_DOWN));
                productStockMapper.updateByPrimaryKey(ps);
            }
        }
        if (oldBill.getStatus() == StockIncomeBill.STATUS_NOMAL) {
            oldBill.setStatus(StockIncomeBill.STATUS_AUDIT);
            oldBill.setAuditor(UserContext.getCurrentUser());
            oldBill.setAuditTime(new Date());
            stockIncomeBillMapper.audit(oldBill);
        }
    }

    @Override
    public StockIncomeBill get(Long id) {
        return stockIncomeBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StockIncomeBill> list() {
        return stockIncomeBillMapper.selectAll();
    }

    @Override
    public PageResult query(StockIncomeBillQueryObject qo) {
        int totalCount = stockIncomeBillMapper.query4Count(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_RESULT;
        }
        List<StockIncomeBill> list = stockIncomeBillMapper.query4List(qo);

        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, list);
    }
}

