package com.fund.client.impl;

import com.fund.api.entity.BankCard;
import com.fund.api.service.BankCardService;
import com.fund.client.mapper.BankCardMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public void addBankCard(BankCard bankCard) {
        bankCardMapper.addBankCard(bankCard);
    }

    @Override
    public List<BankCard> getAllByClientId(int clientId) {
        return bankCardMapper.getAllByClientId(clientId);
    }

    @Override
    public String getBalance(String cardId) {
        //将bigDecimal转为字符串类型返回给前端
        return bankCardMapper.getBalance(cardId).stripTrailingZeros().toPlainString();
    }

    @Override
    public void updateBalance(String cardId, BigDecimal change) {
        bankCardMapper.updateBalance(cardId, change);
    }
}
