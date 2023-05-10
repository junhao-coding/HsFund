package com.fund.business.impl;

import com.fund.api.entity.Client;
import com.fund.api.service.TestService;
import com.fund.business.mapper.TestMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/10  10:04
 */
@CloudComponent
public class TestServiceImpl implements TestService {
    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private TestMapper testMapper;

    @Override
    public Client getClientById(int id) {
        return testMapper.getClientById(id);
    }
}
