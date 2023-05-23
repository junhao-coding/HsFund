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

    List<BigDecimal> getOrdersByPositionId(int year, int month, long positionId);

    Long getPositionId(String productId, int clientId, String cardId);

    /**
     * 根据用户id和产品id查询客户银行卡和所持有的份额
     */
    List<ClientPosition> getPositionPortion(Integer clientId,String productId);

    /**
     * 查询客户产品银行卡所持有的份额
     */
    BigDecimal getTradePortion(Integer clientId,String productId,String cardId);
}
