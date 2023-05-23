package com.fund.api.service;

import com.fund.api.entity.Liquidation;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/21  10:18
 */
@CloudService
public interface LiquidationService {
    void addLiquidation(String productId, BigDecimal netWorthPer);

    /**
     * 根据产品号查询所有单位净值变化
     */
    List<Liquidation> selectAllByProductId(String productId);

    /**
     * 行情更新
     */
    String marketUpdate();
    /**
     * 交易确认
     */
    String businessConfirm();
}
