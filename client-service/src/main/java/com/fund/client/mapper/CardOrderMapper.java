package com.fund.client.mapper;

import com.fund.api.entity.CardOrder;
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
 * @date 2023/05/12  15:43
 */
@Mapper
public interface CardOrderMapper {

    @Insert("insert into card_order(card_order_id, card_id, order_amount) values(#{cardOrderId}, #{cardId}, #{orderAmount})")
    void addCardOrder(CardOrder cardOrder);

    @Select("select order_amount from card_order where card_id = #{cardId} and Year(create_time) = #{year} and Month(create_time) = #{month}")
    List<BigDecimal> getOrdersByCardId(@Param("year") int year, @Param("month") int month, @Param("cardId") long cardId);
}
