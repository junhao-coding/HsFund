package com.fund.liquidation.impl;

import com.fund.api.dto.NetWorthDto;
import com.fund.api.entity.Liquidation;
import com.fund.api.service.LiquidationService;
import com.fund.api.service.ProductService;
import com.fund.api.util.BigDecimalUtil;
import com.fund.api.util.SnowflakeIdGenerator;
import com.fund.api.util.SystemDateUtil;
import com.fund.liquidation.mapper.LiquidationMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/21  10:08
 */
@CloudComponent
@Slf4j
public class LiquidationServiceImpl implements LiquidationService {
    @Autowired
    private LiquidationMapper liquidationMapper;
    @CloudReference
    private ProductService productService;

    @Override
    public void addLiquidation(String productId, BigDecimal netWorthPer) {
        Liquidation liquidation = new Liquidation();
        liquidation.setProductId(productId);
        liquidation.setNetWorthPer(netWorthPer);
        //雪花算法生成ID
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
        liquidation.setLiquidationId(idGenerator.nextId());
        liquidation.setLiquidationDate(SystemDateUtil.current());
        liquidationMapper.addLiquidation(liquidation);
    }

    @Override
    public List<BigDecimal> selectAllByProductId(String productId) {
        return liquidationMapper.selectAllByProductId(productId);
    }

    @Transactional
    @Override
    public void marketUpdate(){
        List<NetWorthDto> netWorthDtoList = productService.getNetWorthDto();
        for (NetWorthDto netWorthDto : netWorthDtoList) {
            BigDecimal rate = BigDecimal.valueOf(RandomUtils.nextDouble(0.9, 1.1));
            netWorthDto.setNetWorth(BigDecimalUtil.mul(rate, netWorthDto.getNetWorth()));
            netWorthDto.setNetWorthPer(BigDecimalUtil.div(netWorthDto.getNetWorth(), netWorthDto.getPortion()));
            //添加一条清算记录
            addLiquidation(netWorthDto.getProductId(), netWorthDto.getNetWorthPer());
        }
        productService.updateNetWorthOld();
        productService.updateNetWorthBatch(netWorthDtoList);
    }
}
