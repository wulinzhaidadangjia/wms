package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.mapper.RoleMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void saveOrUpdate(Role entity, Long[] ids, Long[] mids) {
        if (entity.getId() != null) {
            roleMapper.deleteRelation(entity.getId());
            roleMapper.deleteMenuRelation(entity.getId());
            roleMapper.updateByPrimaryKey(entity);
        } else {
            roleMapper.insert(entity);
        }
        if (ids != null && !"".equals(ids)) {
            for (Long id : ids) {
                roleMapper.insertRelation(entity.getId(), id);
            }
        }
        if (mids != null && !"".equals(mids)) {
            for (Long id : mids) {
                roleMapper.insertMenuRelation(entity.getId(), id);
            }
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            roleMapper.deleteMenuRelation(id);
            roleMapper.deleteByPrimaryKey(id);
            roleMapper.deleteRelation(id);
        }
    }

    @Override
    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> list() {
        return roleMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = roleMapper.query4Count(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_RESULT;
        }
        List<Role> list = roleMapper.query4List(qo);

        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, list);
    }
}
