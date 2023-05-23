package com.fund.business.impl;

import com.fund.api.dto.BusinessDTO;
import com.fund.api.dto.Page;
import com.fund.api.entity.Business;
import com.fund.api.service.BankCardService;
import com.fund.api.service.BusinessService;
import com.fund.api.service.PositionService;
import com.fund.api.util.BusinessIdGenerator;
import com.fund.api.util.ExceptedDayGenerator;
import com.fund.business.exception.BalanceNotEnoughException;
import com.fund.business.mapper.BusinessMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CloudComponent
public class BusinessServiceImpl implements BusinessService {

    @CloudReference
    private BankCardService bankCardService;

    @CloudReference
    private PositionService positionService;

    @Autowired
    private BusinessMapper businessMapper;

    @Transactional
    @Override
    public void addFundPurchase(Business business) {
        business.setBusinessId(BusinessIdGenerator.generateBusinessId());
        business.setExpectedDay(ExceptedDayGenerator.generateExceptedDay());
        business.setTradeType("申购");
        String cardId = business.getCardId();
        BigDecimal amount = business.getTradeAmount();
        //校验余额
        BigDecimal balance = bankCardService.getBalance(cardId);
        if(amount.compareTo(balance)>0) throw new BalanceNotEnoughException("银行卡余额不足");
        //修改银行卡余额
        bankCardService.updateBalance(cardId,amount.negate());
        businessMapper.insertPurchaseFund(business);
    }

    @Override
    public List<Business> getBusinessConfirmed(LocalDate date) {
        return businessMapper.selectBusinessConfirmed(date);
    }

    @Override
    public void confirmBusiness(LocalDate date) {
        businessMapper.confirmBusiness(date);
    }

    @Override
    public void addFundSell(Business business) {
        business.setBusinessId(BusinessIdGenerator.generateBusinessId());
        business.setExpectedDay(ExceptedDayGenerator.generateExceptedDay());
        business.setTradeType("赎回");
        //申购赎回份额
        BigDecimal tradePortion = business.getTradePortion();
        //已提交申购份额
        BigDecimal applySellPortion = businessMapper.getApplySellPortion(business);
        //持有总份额
        BigDecimal totalPortion = positionService.getTradePortion(business.getClientId(), business.getProductId(), business.getCardId());
        if(applySellPortion!=null) tradePortion = tradePortion.add(applySellPortion);
        if(totalPortion!=null&&totalPortion.compareTo(tradePortion)!=1) throw new BalanceNotEnoughException("申购份额已超出赎回上限");
        businessMapper.insertSellFund(business);
    }

    @Override
    public Page<BusinessDTO> getRecords(Map<String,Object> map) {
        int pageNum = Integer.parseInt(map.get("pageNum").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessDTO> allBusiness = businessMapper.getAllBusiness(map);
        PageInfo<BusinessDTO> pageInfo = new PageInfo<>(allBusiness);
        return new Page<>(pageInfo.getList(),pageInfo.getTotal(),pageInfo.getPages());
    }

    @Transactional
    @Override
    public void withdrawBusiness(String businessId) {
        Business business = businessMapper.getBusinessByBusinessId(businessId);
        String type = business.getTradeType();
        if("申购".equals(type)){
            BigDecimal tradeAmount = business.getTradeAmount();
            bankCardService.updateBalance(business.getCardId(),tradeAmount);
        }
        businessMapper.withdrawBusiness(businessId);

    }
}
