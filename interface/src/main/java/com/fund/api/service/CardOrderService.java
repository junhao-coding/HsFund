package com.fund.api.service;

import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  15:53
 */
@CloudService
public interface CardOrderService {
    void addCardOrder(String cardId, BigDecimal orderAmount);

    List<String> getOrdersByCardId(int year, int month, long cardId);
}
