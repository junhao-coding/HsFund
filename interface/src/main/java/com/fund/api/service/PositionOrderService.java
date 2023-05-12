package com.fund.api.service;

import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.math.BigDecimal;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  16:48
 */
@CloudService
public interface PositionOrderService {
    void addPositionOrder(long positionId, BigDecimal portion);
}
