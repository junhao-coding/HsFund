package com.fund.business.mapper;

import com.fund.api.dto.BusinessDTO;
import com.fund.api.entity.Business;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface BusinessMapper {
    /**
     * 将申购申请写入数据库
     */
    void insertPurchaseFund(Business business);

    /**
     * 将赎回申请写入数据库
     */
    void insertSellFund(Business business);

    /**
     * 查询所有需要被确认的交易
     */
    @Select("select client_id, product_id, trade_type, card_id, trade_portion, trade_amount from business " +
            "where expected_day = #{date} and is_withdrawn = 0")
    List<Business> selectBusinessConfirmed(LocalDate date);

    /**
     * 确认交易
     */
    @Update("update business set is_confirmed = 1 where expected_day = #{date} and is_withdrawn = 0")
    void confirmBusiness(LocalDate date);

    /**
     * 根据用户id产品id银行卡id查询未确认的所有赎回份额
     */

    BigDecimal getApplySellPortion(Business business);

    /**
     * 交易查询
     */

    List<BusinessDTO> getAllBusiness(Map<String,Object> map);

    /**
     * 交易撤回
     */
    void withdrawBusiness(String businessId);

    /**
     * 根据ID查询对应的交易
     */
    Business getBusinessByBusinessId(String businessId);
}
