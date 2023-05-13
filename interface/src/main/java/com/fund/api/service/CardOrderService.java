package com.fund.api.service;

import com.fund.api.entity.CardOrder;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import javax.smartcardio.Card;
import java.math.BigDecimal;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  15:53
 */
@CloudService
public interface CardOrderService {
    void addCardOrder(String cardId, BigDecimal orderAmount);
}
