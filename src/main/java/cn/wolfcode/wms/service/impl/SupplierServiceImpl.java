package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.mapper.SupplierMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public void saveOrUpdate(Supplier entity) {
        if (entity.getId() != null) {
            supplierMapper.updateByPrimaryKey(entity);
        } else {
            supplierMapper.insert(entity);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            supplierMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Supplier get(Long id) {
        return supplierMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Supplier> list() {
        return supplierMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = supplierMapper.query4Count(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_RESULT;
        }
        List<Supplier> list = supplierMapper.query4List(qo);

        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, list);
    }
}

