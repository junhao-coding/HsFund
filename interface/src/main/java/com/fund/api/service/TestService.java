package com.fund.api.service;

import com.fund.api.entity.Client;
import com.hundsun.jrescloud.rpc.annotation.CloudFunction;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: 用于测试数据库和zk的连接
 * @date 2023/05/10  9:19
 */
@CloudService(validation = true)
public interface TestService {
    @CloudFunction("getClientById")
    Client getClientById(int id);
}
