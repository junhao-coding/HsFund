package com.fund.business.mapper;

import com.fund.api.dto.BusinessDto;
import com.fund.api.entity.Business;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface BusinessMapper {
    /**
     * 将申购申请写入数据库
     * @param business
     */
    void insertPurchaseFund(Business business);

    /**
     * 查询所有需要被确认的交易
     */
    @Select("select client_id, product_id, trade_type, card_id, trade_portion, trade_amount from business " +
            "where expected_day = #{date} and is_withdrawn = 0")
    List<BusinessDto> selectBusinessConfirmed(LocalDate date);

    @Update("update business set is_confirmed = 1 where expected_day = #{date} and is_withdrawn = 0")
    void confirmBusiness(LocalDate date);
}
