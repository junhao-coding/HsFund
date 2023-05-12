package com.fund.client.impl;

import com.fund.api.entity.ClientPosition;
import com.fund.api.service.PositionService;
import com.fund.api.util.SnowflakeIdGenerator;
import com.fund.client.mapper.PositionMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

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

    @Override
    public void addPosition(ClientPosition position) {
        //雪花算法生成ID
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
        long id = idGenerator.nextId();
        position.setPositionId(id);
        positionMapper.addPosition(position);
    }

    @Override
    public void updatePosition(long positionId, BigDecimal changePosition) {
        positionMapper.updatePosition(positionId, changePosition);
    }
}
