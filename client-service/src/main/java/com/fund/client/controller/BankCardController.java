package com.fund.client.controller;

import com.fund.api.dto.Result;
import com.fund.api.entity.BankCard;
import com.fund.api.service.BankCardService;
import com.fund.api.service.CardOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  14:27
 */
@RestController
@RequestMapping("/fund/bankCard")
public class BankCardController {
    @Autowired
    private BankCardService bankCardService;

    @Autowired
    private CardOrderService cardOrderService;


    @PostMapping
    public Result addBankCard(@RequestBody @Valid BankCard bankCard){
        bankCardService.addBankCard(bankCard);
        return Result.ok();
    }

    @GetMapping("/{clientId}")
    public Result getBankCards(@PathVariable("clientId") int clientId){
        List<BankCard> bankCards = bankCardService.getAllByClientId(clientId);
        if(bankCards == null || bankCards.size() == 0){
            return Result.fail("该用户未绑定任何银行卡");
        }
        return Result.ok(bankCards);
    }

    @GetMapping("/balance/{cardId}")
    public Result getBalance(@PathVariable("cardId") String cardId){
        return Result.ok(bankCardService.getBalance(cardId));
    }
    @PutMapping
    @Transactional
    public Result updateBalance(@RequestParam(value = "cardId") String cardId,
                                @RequestParam(value = "change" , defaultValue = "0") String change){
        bankCardService.updateBalance(cardId, new BigDecimal(change));
        cardOrderService.addCardOrder(cardId, new BigDecimal(change));
        return Result.ok();
    }

    @GetMapping("/order")
    public Result getOrdersByPositionId(@RequestParam("year") int year,
                                        @RequestParam("month") int month,
                                        @RequestParam("cardId") long cardId){
        List<String> cardOrders = cardOrderService.getOrdersByCardId(year, month, cardId);
        return Result.ok(cardOrders);
    }
}
