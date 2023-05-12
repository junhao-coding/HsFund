package com.fund.client.controller;

import com.fund.api.dto.Result;
import com.fund.api.service.CardOrderService;
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
 * @date 2023/05/12  16:12
 */
@RestController
@RequestMapping("/fund/cardOrder")
public class CardOrderController {
    @Autowired
    private CardOrderService cardOrderService;

    @PostMapping()
    public Result addCardOrder(@Param("cardId") String cardId,
                               @Param("orderAmount") String orderAmount){
        cardOrderService.addCardOrder(cardId, new BigDecimal(orderAmount));
        return Result.ok();
    }
}
