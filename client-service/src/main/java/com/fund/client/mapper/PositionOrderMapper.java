package com.fund.client.mapper;

import com.fund.api.entity.PositionOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  16:45
 */
@Mapper
public interface PositionOrderMapper {

    @Insert("insert into position_order(position_order_id, position_id, portion) " +
            "values(#{positionOrderId}, #{positionId}, #{portion})")
    void addPositionOrder(PositionOrder positionOrder);
}
