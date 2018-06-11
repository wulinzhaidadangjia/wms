package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.mapper.BrandMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public void saveOrUpdate(Brand entity) {
        if (entity.getId() != null) {
            brandMapper.updateByPrimaryKey(entity);
        } else {
            brandMapper.insert(entity);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            brandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Brand get(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Brand> list() {
        return brandMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = brandMapper.query4Count(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_RESULT;
        }
        List<Brand> list = brandMapper.query4List(qo);

        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, list);
    }
}

