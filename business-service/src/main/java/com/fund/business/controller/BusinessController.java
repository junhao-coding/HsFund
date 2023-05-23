package com.fund.business.controller;


import com.fund.api.dto.BusinessDTO;
import com.fund.api.dto.Page;
import com.fund.api.dto.Result;
import com.fund.api.entity.Business;
import com.fund.api.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


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

    @GetMapping("/withdraw/{businessId}")
    public Result withdrawBusiness(@PathVariable String businessId){
        businessService.withdrawBusiness(businessId);
        return Result.ok();
    }

    @GetMapping("/records")
    public Result getRecordsConditional(@RequestParam Map<String,Object> map){
        Page<BusinessDTO> records = businessService.getRecords(map);
        return Result.ok(records);
    }

    @PostMapping("/sell")
    public Result fundSell(@RequestBody Business business){
        System.out.println(business);
        businessService.addFundSell(business);
        System.out.println(business);
        return Result.ok(business);
    }
}
