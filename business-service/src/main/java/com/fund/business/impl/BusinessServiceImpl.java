package com.fund.business.impl;

import com.fund.api.entity.Business;
import com.fund.api.service.BankCardService;
import com.fund.api.service.BusinessService;
import com.fund.api.service.CardOrderService;
import com.fund.api.util.BusinessIdGenerator;
import com.fund.api.util.ExceptedDayGenerator;
import com.fund.business.exception.BalanceNotEnoughException;
import com.fund.business.mapper.BusinessMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

@CloudComponent
public class BusinessServiceImpl implements BusinessService {

    @CloudReference
    private BankCardService bankCardService;

    @CloudReference
    private CardOrderService cardOrderService;

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
        //流水表记录
        cardOrderService.addCardOrder(cardId,amount.negate());
        businessMapper.insertPurchaseFund(business);
    }

}
