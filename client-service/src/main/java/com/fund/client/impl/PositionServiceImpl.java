package com.fund.client.impl;

import com.fund.api.entity.ClientPosition;
import com.fund.api.entity.PositionOrder;
import com.fund.api.service.PositionService;
import com.fund.api.util.SnowflakeIdGenerator;
import com.fund.client.mapper.PositionMapper;
import com.fund.client.mapper.PositionOrderMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  16:27
 */
@CloudComponent
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private PositionOrderMapper positionOrderMapper;

    private  SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);

    @Override
    @Transactional
    public void addPosition(ClientPosition position) {
        //雪花算法生成ID, 插入客户持仓表
        position.setPositionId(idGenerator.nextId());
        positionMapper.addPosition(position);
        //插入持仓流水表
        addPositionOrder(position.getPositionId(), position.getPortion());
    }

    @Override
    @Transactional
    public void updatePosition(long positionId, BigDecimal changePosition) {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
        //更新持仓表
        positionMapper.updatePosition(positionId, changePosition.setScale(4, RoundingMode.HALF_UP));
        //插入持仓流水表
        addPositionOrder(positionId, changePosition.setScale(4, RoundingMode.HALF_UP));
    }
    private void addPositionOrder(long positionId, BigDecimal changePosition){
        PositionOrder positionOrder = new PositionOrder();
        positionOrder.setPositionOrderId(idGenerator.nextId());
        positionOrder.setPortion(changePosition);
        positionOrder.setPositionId(positionId);
        positionOrderMapper.addPositionOrder(positionOrder);
    }

    @Override
    public List<ClientPosition> getPositionsByClientId(int clientId) {
        return positionMapper.getPositionsByClientId(clientId);
    }

    @Override
    public List<BigDecimal> getOrdersByPositionId(int year, int month, long positionId) {
        List<BigDecimal> orderList = positionOrderMapper.getOrdersByPositionId(year, month, positionId);
        return orderList;
    }

    @Override
    public Long getPositionId(String productId, int clientId, String cardId) {
        return positionMapper.selectPositionId(productId, clientId, cardId);
    }

    @Override
    public List<ClientPosition> getPositionPortion(Integer clientId, String productId) {
        List<ClientPosition> positionPortion = positionMapper.getPositionPortion(clientId, productId);
        return positionPortion;
    }

    @Override
    public BigDecimal getTradePortion(Integer clientId, String productId, String cardId) {
        return positionMapper.getTradePortion(clientId, productId, cardId);
    }
}
