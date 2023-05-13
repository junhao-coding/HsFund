package com.fund.client.impl;


import com.fund.api.entity.CardOrder;
import com.fund.api.service.CardOrderService;
import com.fund.api.util.SnowflakeIdGenerator;
import com.fund.client.mapper.CardOrderMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  15:55
 */
@CloudComponent
public class CardOrderServiceImpl implements CardOrderService {
    @Autowired
    private CardOrderMapper cardOrderMapper;


    @Override
    public void addCardOrder(String cardId, BigDecimal orderAmount) {
        CardOrder cardOrder = new CardOrder();
        cardOrder.setCardId(cardId);
        cardOrder.setOrderAmount(orderAmount);
        //雪花算法生成ID
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
        long id = idGenerator.nextId();
        cardOrder.setCardOrderId(id);
        cardOrderMapper.addCardOrder(cardOrder);
    }
}