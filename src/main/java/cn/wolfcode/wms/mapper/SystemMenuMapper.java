package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.SystemMenuOueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMenu entity);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll(SystemMenuOueryObject qo);

    int updateByPrimaryKey(SystemMenu entity);

    List<SystemMenu> getAllMenus();

    List<Map<String, Object>> selectMenuByparentId(@Param("parentSn") String parentSn, @Param("parentId") Long parentId);
}