package com.fund.client.mapper;

import com.fund.api.entity.Client;
import com.fund.api.entity.ClientPosition;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

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

    @Select("select product_id, card_id, risk_mismatch, portion, create_time, update_time from client_position " +
            "where client_id = #{clientId}")
    List<ClientPosition> getPositionsByClientId(@Param("clientId") int clientId);

    @Select("select position_id from client_position " +
            "where product_id = #{productId} " +
            "and client_id = #{clientId} " +
            "and card_id = #{cardId}")
    Long selectPositionId(@Param("productId") String productId,
                          @Param("clientId") int clientId,
                          @Param("cardId") String cardId);
}
