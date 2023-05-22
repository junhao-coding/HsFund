package com.fund.api.service;

import com.fund.api.entity.ClientPosition;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  16:23
 */
@CloudService(validation = true)
public interface PositionService {
    void addPosition(ClientPosition position);

    void updatePosition(long positionId, BigDecimal changePosition);

    List<ClientPosition> getPositionsByClientId(int clientId);

    List<String> getOrdersByPositionId(int year, int month, long positionId);
}
