package com.fund.client.mapper;

import com.fund.api.entity.CardOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
