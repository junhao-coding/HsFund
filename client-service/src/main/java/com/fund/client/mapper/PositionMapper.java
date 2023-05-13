package com.fund.client.mapper;

import com.fund.api.entity.ClientPosition;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  15:44
 */
@Mapper
public interface PositionMapper {
    @Insert("insert into client_position(position_id, product_id, client_id, card_id, risk_mismatch, portion) " +
            "values (#{positionId}, #{productId}, #{clientId}, #{cardId}, #{riskMismatch}, #{portion})")
    void addPosition(ClientPosition position);

    @Update("update client_position set portion = portion + #{changePosition} where position_id = #{positionId}")
    void updatePosition(@Param("positionId") long positionId, @Param("changePosition") BigDecimal changePosition);
}
