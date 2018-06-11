package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role entity);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role entity);

    int query4Count(QueryObject qo);

    List<Role> query4List(QueryObject qo);

    //维护与permission中间表的关系

    /**
     * 删除与permission表的中间表数据关系
     *
     * @param role_id
     */
    void deleteRelation(Long role_id);

    void insertRelation(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    void deleteMenuRelation(Long id);

    void insertMenuRelation(@Param("roleId") Long roleId, @Param("menuId") Long menuId);
}