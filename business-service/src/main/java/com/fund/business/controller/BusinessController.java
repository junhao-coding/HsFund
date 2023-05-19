package com.fund.business.controller;


import com.fund.api.dto.Result;
import com.fund.api.entity.Business;
import com.fund.api.service.BankCardService;
import com.fund.api.service.BusinessService;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/fund/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;



    @PostMapping("/purchase")
    public Result fundPurchase(@RequestBody Business business){
        System.out.println(business);
        businessService.addFundPurchase(business);
        System.out.println(business);
        return Result.ok(business);
    }
}
