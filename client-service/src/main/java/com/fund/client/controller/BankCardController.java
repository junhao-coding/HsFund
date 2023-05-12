package com.fund.client.controller;

import com.fund.api.dto.Result;
import com.fund.api.entity.BankCard;
import com.fund.api.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Result addBankCard(@RequestBody @Valid BankCard bankCard){
        bankCardService.addBankCard(bankCard);
        return Result.ok();
    }

    @GetMapping("/{clientId}")
    public Result getBankCardsPage(@PathVariable("clientId") int clientId){
        List<BankCard> bankCards = bankCardService.getAllByClientId(clientId);
        return Result.ok(bankCards, (long) bankCards.size());
    }
    @PutMapping()
    public Result updateBalance(@RequestParam(value = "cardId") String cardId,
                                @RequestParam(value = "change" , defaultValue = "0") String change){
        bankCardService.updateBalance(cardId, new BigDecimal(change));
        return Result.ok();
    }

}
