package com.fund.client.impl;

import com.fund.api.entity.ClientPosition;
import com.fund.api.entity.PositionOrder;
import com.fund.api.service.PositionService;
import com.fund.api.util.SnowflakeIdGenerator;
import com.fund.client.mapper.PositionMapper;
import com.fund.client.mapper.PositionOrderMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

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


    @Override
    public void addPosition(ClientPosition position) {
        //雪花算法生成ID
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
        //插入客户持仓表
        position.setPositionId(idGenerator.nextId());
        positionMapper.addPosition(position);
        //插入持仓流水表
        PositionOrder positionOrder = new PositionOrder();
        positionOrder.setPositionOrderId(idGenerator.nextId());
        positionOrder.setPortion(position.getPortion());
        positionOrder.setPositionId(position.getPositionId());
        positionOrderMapper.addPositionOrder(positionOrder);
    }

    @Override
    public void updatePosition(long positionId, BigDecimal changePosition) {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
        //更新持仓表
        positionMapper.updatePosition(positionId, changePosition);
        //插入持仓流水表
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
    public List<String> getOrdersByPositionId(int year, int month, long positionId) {
        List<BigDecimal> orderList = positionOrderMapper.getOrdersByPositionId(year, month, positionId);
        return orderList.stream().map((position) -> position.stripTrailingZeros().toPlainString()).collect(toList());
    }
}
