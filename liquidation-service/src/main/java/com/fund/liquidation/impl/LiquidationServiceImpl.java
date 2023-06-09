package com.fund.liquidation.impl;

import com.fund.api.dto.NetWorthDTO;
import com.fund.api.entity.Business;
import com.fund.api.entity.ClientPosition;
import com.fund.api.entity.Liquidation;
import com.fund.api.entity.Product;
import com.fund.api.service.*;
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

    @CloudReference
    private BusinessService businessService;

    @CloudReference
    private BankCardService bankCardService;

    @CloudReference
    private PositionService positionService;

    @CloudReference
    private ClientService clientService;

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
    public List<Liquidation> selectAllByProductId(String productId) {
        return liquidationMapper.selectAllByProductId(productId);
    }

    @Transactional
    @Override
    public String marketUpdate(){
        List<NetWorthDTO> netWorthDtoList = productService.getNetWorthDto();
        for (NetWorthDTO netWorthDto : netWorthDtoList) {
            BigDecimal rate = BigDecimal.valueOf(RandomUtils.nextDouble(0.9, 1.1));
            netWorthDto.setNetWorth(BigDecimalUtil.mul(rate, netWorthDto.getNetWorth()));
            netWorthDto.setNetWorthPer(BigDecimalUtil.div(netWorthDto.getNetWorth(), netWorthDto.getPortion()));
            //添加一条清算记录
            addLiquidation(netWorthDto.getProductId(), netWorthDto.getNetWorthPer());
        }
        productService.updateNetWorthOld();
        productService.updateNetWorthBatch(netWorthDtoList);
        return "市场行情更新成功";
    }

    /**
     * 交易确认 申购申请调用 purchaseConfirm()
     *         赎回申请调用 redeemConfirm()
     */
    @Transactional
    @Override
    public String businessConfirm() {
        List<Business> confirmedList = businessService.getBusinessConfirmed(SystemDateUtil.current());
        //没有任何交易直接退出
        if(confirmedList == null || confirmedList.size() == 0){
            return "昨天没有产生任何交易";
        }
        try {
            for(Business business : confirmedList){
                if(business.getTradeType().equals("申购")){
                    purchaseConfirm(business);
                }
                if(business.getTradeType().equals("赎回")){
                    redeemConfirm(business);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "交易确认发生错误";
        }
        businessService.confirmBusiness(SystemDateUtil.current());
        return "所有交易全部确认完成";
    }

    private void purchaseConfirm(Business business){
        BigDecimal tradeAmount = business.getTradeAmount();
        String productId = business.getProductId();
        Product product = productService.getProductById(productId);
        // 计算得到单位净值
        BigDecimal temp = BigDecimalUtil.div(product.getNetWorth(), product.getPortion());
        // 申购得到的份额
        BigDecimal tradePortion = BigDecimalUtil.div(tradeAmount, temp);
        // 基金产品份额增加
        product.setPortion(BigDecimalUtil.add(tradePortion, product.getPortion()));
        productService.updateProduct(product);
        // 客户持仓增加
        Integer clientId = business.getClientId();
        String cardId = business.getCardId();
        Long positionId = positionService.getPositionId(productId, clientId, cardId);
        if(positionId == null){
            // 客户没有当前持仓 则新建持仓
            ClientPosition clientPosition = new ClientPosition();
            clientPosition.setClientId(clientId);
            clientPosition.setProductId(productId);
            clientPosition.setCardId(cardId);
            clientPosition.setPortion(tradePortion);
            // 风险等级判定
            if(product.getRiskLevel() > clientService.selectClientById(clientId).getRiskLevel()){
                clientPosition.setRiskMismatch(false);
            }
            clientPosition.setRiskMismatch(true);
            clientPosition.setPortion(tradePortion);
            positionService.addPosition(clientPosition);
        }else{
            positionService.updatePosition(positionId, tradePortion);
        }
    }

    private void redeemConfirm(Business business){
        BigDecimal tradePortion = business.getTradePortion();
        String productId = business.getProductId();
        Product product = productService.getProductById(productId);
        // 计算得到单位净值
        BigDecimal temp = BigDecimalUtil.div(product.getNetWorth(), product.getPortion());
        // 赎回份额得到的金额
        BigDecimal tradeAmount = BigDecimalUtil.mul(temp, tradePortion);
        // 基金产品份额减少
        product.setPortion(BigDecimalUtil.sub(product.getPortion(), tradePortion));
        productService.updateProduct(product);
        // 银行卡到账
        bankCardService.updateBalance(business.getCardId(), tradeAmount);
        // 持仓减少
        Long positionId = positionService.getPositionId(productId, business.getClientId(), business.getCardId());
        positionService.updatePosition(positionId, tradePortion.negate());
    }
}
