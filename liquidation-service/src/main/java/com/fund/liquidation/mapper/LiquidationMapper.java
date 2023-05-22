package com.fund.liquidation.mapper;

import com.fund.api.entity.Liquidation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/21  9:54
 */
@Mapper
public interface LiquidationMapper {
    @Insert("insert into liquidation(liquidation_id, product_id, net_worth_per, liquidation_date) " +
            "values (#{liquidationId}, #{productId}, #{netWorthPer}, #{liquidationDate})")
    void addLiquidation(Liquidation liquidation);

    @Select("select net_worth_per from liquidation where product_id = #{ProductId} limit 15")
    List<BigDecimal> selectAllByProductId(String productId);
}
