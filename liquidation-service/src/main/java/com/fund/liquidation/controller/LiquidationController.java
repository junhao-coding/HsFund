package com.fund.liquidation.controller;

import com.fund.api.dto.Result;
import com.fund.api.entity.Liquidation;
import com.fund.api.service.LiquidationService;
import com.fund.api.util.SystemDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/19  17:27
 */
@RestController
@RequestMapping("/fund/liquidate")
public class LiquidationController {
    @Autowired
    private LiquidationService liquidationService;

    @PutMapping("/dayInitial")
    public Result dayInitial(){
        SystemDateUtil.dayInitial();
        return Result.ok(SystemDateUtil.current());
    }
    @GetMapping("/current")
    public Result current(){
        return Result.ok(SystemDateUtil.current());
    }

    @GetMapping("/netWorthPer/{productId}")
    public Result getAllByProductId(@PathVariable String productId){
        List<Liquidation> worthList = liquidationService.selectAllByProductId(productId);
        if(worthList == null || worthList.size() == 0){
            return Result.fail("该产品不存在或该产品目前还没有净值数据");
        }
        return Result.ok(worthList);
    }

    @PutMapping("/marketUpdate")
    public Result marketUpdate(){
        CompletableFuture<String> futureMarket = new CompletableFuture<>();
        new Thread(() -> {
            String description = liquidationService.marketUpdate();
            futureMarket.complete(description);
        }).start();
        return Result.ok(futureMarket);
    }

    @PutMapping("/confirm")
    public Result businessConfirm(){
        CompletableFuture<String> futureConfirm = new CompletableFuture<>();
        new Thread(() -> {
            String description = liquidationService.businessConfirm();
            futureConfirm.complete(description);
        }).start();
        return Result.ok(futureConfirm);
    }
}
