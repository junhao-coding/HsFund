package com.fund.client.impl;

import com.fund.api.entity.CardOrder;
import com.fund.api.entity.PositionOrder;
import com.fund.api.service.PositionOrderService;
import com.fund.api.util.SnowflakeIdGenerator;
import com.fund.client.mapper.CardOrderMapper;
import com.fund.client.mapper.PositionMapper;
import com.fund.client.mapper.PositionOrderMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  16:50
 */
@CloudComponent
public class PositionOrderServiceImpl implements PositionOrderService {
    @Autowired
    private PositionOrderMapper positionOrderMapper;


    @Override
    public void addPositionOrder(long positionId, BigDecimal portion) {
        PositionOrder positionOrder = new PositionOrder();
        positionOrder.setPositionId(positionId);
        positionOrder.setPortion(portion);
        //雪花算法生成ID
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
        long id = idGenerator.nextId();
        positionOrder.setPositionOrderId(id);
        positionOrderMapper.addPositionOrder(positionOrder);
    }
}
