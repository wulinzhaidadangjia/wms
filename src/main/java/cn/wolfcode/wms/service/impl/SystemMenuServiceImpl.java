package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.mapper.SystemMenuMapper;
import cn.wolfcode.wms.query.SystemMenuOueryObject;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Override
    public List<Map<String, Object>> loadMenus(String parentSn) {
        if (UserContext.getCurrentUser().isAdmin()) {
            return systemMenuMapper.selectMenuByparentId(parentSn, null);
        } else {
            return systemMenuMapper.selectMenuByparentId(parentSn, UserContext.getCurrentUser().getId());
        }
    }

    @Override
    public List<SystemMenu> selectAllMenu() {
        return systemMenuMapper.getAllMenus();
    }

    @Override
    public void saveOrUpdate(SystemMenu entity) {
        if (entity.getId() != null) {
            systemMenuMapper.updateByPrimaryKey(entity);
        } else {
            systemMenuMapper.insert(entity);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            systemMenuMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public SystemMenu get(Long id) {
        return systemMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemMenu> list(SystemMenuOueryObject qo) {
        return systemMenuMapper.selectAll(qo);
    }

    @Override
    public List<SystemMenu> getParentMenus(SystemMenu sm) {
        List<SystemMenu> list = new ArrayList<>();
        list.add(sm);
        //将当前菜单及其父菜单封装到集合中.
        while (sm.getParent() != null) {
            SystemMenu parent = sm.getParent();
            list.add(parent);
        }
        Collections.reverse(list);
        return list;
    }
}
