package com.fund.client.controller;

import com.fund.api.dto.Result;
import com.fund.api.service.PositionOrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  16:54
 */
@RestController
@RequestMapping("/fund/positionOrder")
public class PositionOrderController {
    @Autowired
    private PositionOrderService positionOrderService;

    @PostMapping
    public Result addCardOrder(@Param("positionId") long positionId,
                               @Param("Portion") String portion){
        positionOrderService.addPositionOrder(positionId, new BigDecimal(portion));
        return Result.ok();
    }
}
