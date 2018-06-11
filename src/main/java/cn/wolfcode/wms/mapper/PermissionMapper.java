package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission entity);

    List<Permission> selectAll();

    int query4Count(QueryObject qo);

    List<Permission> query4List(QueryObject qo);

    List<String> seletAllExpression();
}