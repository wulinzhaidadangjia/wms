package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.mapper.ClientMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public void saveOrUpdate(Client entity) {
        if (entity.getId() != null) {
            clientMapper.updateByPrimaryKey(entity);
        } else {
            clientMapper.insert(entity);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            clientMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Client get(Long id) {
        return clientMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Client> list() {
        return clientMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = clientMapper.query4Count(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_RESULT;
        }
        List<Client> list = clientMapper.query4List(qo);

        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, list);
    }
}

