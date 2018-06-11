package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.mapper.DepartmentMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void saveOrUpdate(Department entity) {
        if (entity.getId() != null) {
            departmentMapper.updateByPrimaryKey(entity);
        } else {
            departmentMapper.insert(entity);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            departmentMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Department get(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> list() {
        return departmentMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = departmentMapper.query4Count(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_RESULT;
        }
        List<Department> list = departmentMapper.query4List(qo);

        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, list);
    }
}

