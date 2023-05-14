package com.fund.client.mapper;

import com.fund.api.entity.BankCard;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  13:59
 */
@Mapper
public interface BankCardMapper {
    @Insert("insert into bank_card(card_id,client_id, balance) values (#{cardId}, #{clientId}, #{balance})")
    void addBankCard(BankCard bankCard);

    @Select("select card_id, client_id, balance, create_time, update_time from bank_card where client_id = #{clientId}")
    List<BankCard> getAllByClientId(int clientId);

    @Select("select balance from bank_card where card_id = #{cardId} ")
    BigDecimal getBalance(String cardId);

    @Update("update bank_card set balance = balance + #{change} where card_id = #{cardId}")
    void updateBalance(@Param("cardId") String cardId, @Param("change") BigDecimal change);
}
