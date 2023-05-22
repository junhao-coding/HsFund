package com.fund.client.impl;

import com.fund.api.entity.BankCard;
import com.fund.api.entity.CardOrder;
import com.fund.api.service.BankCardService;
import com.fund.api.util.SnowflakeIdGenerator;
import com.fund.client.mapper.BankCardMapper;
import com.fund.client.mapper.CardOrderMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  14:18
 */
@CloudComponent
public class BankCardServiceImpl implements BankCardService {
    @Autowired
    private BankCardMapper bankCardMapper;
    @Autowired
    private CardOrderMapper cardOrderMapper;

    @Override
    public void addBankCard(BankCard bankCard) {
        bankCardMapper.addBankCard(bankCard);
    }

    @Override
    public List<BankCard> getAllByClientId(int clientId) {
        return bankCardMapper.getAllByClientId(clientId);
    }

    @Override
    public BigDecimal getBalance(String cardId) {
        //将bigDecimal转为字符串类型返回给前端
        return bankCardMapper.getBalance(cardId);
    }

    @Override
    @Transactional
    public void updateBalance(String cardId, BigDecimal change) {
        bankCardMapper.updateBalance(cardId, change);
        addCardOrder(cardId, change);
    }

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

    @Override
    public List<String> getOrdersByCardId(int year, int month, long cardId) {
        List<BigDecimal> orderList = cardOrderMapper.getOrdersByCardId(year, month, cardId);
        return orderList.stream().map((position) -> position.stripTrailingZeros().toPlainString()).collect(Collectors.toList());
    }
}

