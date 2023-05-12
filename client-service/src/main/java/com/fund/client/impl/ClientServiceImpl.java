package com.fund.client.impl;

import com.fund.api.dto.Page;
import com.fund.api.entity.Client;
import com.fund.api.service.ClientService;
import com.fund.client.mapper.ClientMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/11  16:31
 */
@Component
public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public void addClient(Client client) {
        clientMapper.addClient(client);
    }

    @Override
    public void updateClient(Client client) {
        clientMapper.updateClient(client);
    }

    @Override
    public void deleteClientById(int id) {
        clientMapper.deleteClientById(id);
    }

    @Override
    public Client selectClientById(int id) {
        return clientMapper.getClientById(id);
    }

    @Override
    public Client selectClientByName(String name) {
        return clientMapper.getClientByName(name);
    }

    @Override
    public Page<Client> selectClientByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Client> clientList = clientMapper.getClientAll();
        PageInfo<Client> clientPageInfo = new PageInfo<>(clientList);
        return new Page<>(clientList, clientPageInfo.getTotal(), clientPageInfo.getPages());
    }
}
