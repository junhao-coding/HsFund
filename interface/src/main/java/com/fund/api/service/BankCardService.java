package com.fund.api.service;

import com.fund.api.entity.BankCard;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  14:18
 */
@CloudService
public interface BankCardService {
    void addBankCard(BankCard bankCard);

    List<BankCard> getAllByClientId(int clientId);

    String getBalance(String cardId);

    void updateBalance(String clientId, BigDecimal change);
}
