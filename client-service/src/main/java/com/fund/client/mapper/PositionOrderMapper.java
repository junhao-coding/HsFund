package com.fund.client.mapper;

import com.fund.api.entity.PositionOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

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

    @Select("select portion from position_order where position_id = #{positionId} and Year(create_time) = #{year} and Month(create_time) = #{month}")
    List<BigDecimal> getOrdersByPositionId(@Param("year") int year, @Param("month") int month, @Param("positionId") long positionId);
}
