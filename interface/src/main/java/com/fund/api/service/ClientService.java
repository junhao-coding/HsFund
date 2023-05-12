package com.fund.api.service;

import com.fund.api.dto.Page;
import com.fund.api.entity.Client;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/11  16:26
 */
@CloudService(validation = true)
public interface ClientService {
    void addClient(Client client);

    void updateClient(Client client);

    void deleteClientById(int id);

    Client selectClientById(int id);

    Client selectClientByName(String name);

    Page<Client> selectClientByPage(int pageNum, int pageSize);
}
