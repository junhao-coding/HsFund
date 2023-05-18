package com.fund.api.service;

import com.fund.api.dto.ClientDTO;
import com.fund.api.dto.Page;
import com.fund.api.entity.Client;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.util.List;
import java.util.Map;

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

    List<String> selectClientByLikeName(String likeName);

    Page<Client> selectClientByPage(int pageNum, int pageSize);

    /**
     * 通过客户代号或姓名模糊搜索客户信息包括所持有的所有银行卡信息
     * @param keyword
     * @return
     */
    List<ClientDTO> selectClientLikely(String keyword);
}
