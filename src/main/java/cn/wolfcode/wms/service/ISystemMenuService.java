package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.SystemMenuOueryObject;

import java.util.List;
import java.util.Map;

public interface ISystemMenuService {
    void saveOrUpdate(SystemMenu entity);

    void delete(Long id);

    SystemMenu get(Long id);

    List<SystemMenu> list(SystemMenuOueryObject qo);

    /**
     * 获取所有的父菜单,用于在菜单索引,
     * @param parent
     * @return
     */
    List<SystemMenu> getParentMenus(SystemMenu parent);

    List<SystemMenu> selectAllMenu();

    List<Map<String, Object>> loadMenus(String parentSn);
}
