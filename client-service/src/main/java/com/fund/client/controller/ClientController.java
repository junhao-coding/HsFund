package com.fund.client.controller;

import com.fund.api.dto.ClientDTO;
import com.fund.api.dto.Page;
import com.fund.api.dto.Result;
import com.fund.api.entity.BankCard;
import com.fund.api.entity.Client;
import com.fund.api.service.BankCardService;
import com.fund.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/11  16:46
 */
@RestController
@RequestMapping("/fund/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private BankCardService bankCardService;

    @GetMapping("/likely")
    public Result getClientLikely(String keyword){
        List<ClientDTO> clientDTOS = clientService.selectClientLikely(keyword);
        return Result.ok(clientDTOS);
    }

    @GetMapping("/{id}")
    public Result getClientById(@PathVariable("id") int id){
        Client client = clientService.selectClientById(id);
        if(client == null){
            return Result.fail("不存在该用户");
        }
        return Result.ok(client);
    }

    @PostMapping()
    public Result addClient(@RequestBody ClientDTO clientDto){
        int clientId = clientService.addClient(clientDto);
        List<BankCard> bankCardList = clientDto.getBankCardList();
        if(bankCardList == null ||bankCardList.size() == 0){
            return Result.fail("请至少录入一张银行卡");
        }
        //为每张银行卡绑定开户客户
        for (BankCard card : bankCardList){
            card.setClientId(clientId);
            bankCardService.addBankCard(card);
        }
        return Result.ok();
    }
    @PutMapping()
    public Result updateClient(@RequestBody @Valid Client client){
        clientService.updateClient(client);
        return Result.ok();
    }
    @DeleteMapping("/{id}")
    public Result deleteClient(@PathVariable("id") int id){
        clientService.deleteClientById(id);
        return Result.ok();
    }
    @GetMapping("/page")
    public Result getClientByPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        Page<Client> clientPage = clientService.selectClientByPage(pageNum, pageSize);
        return Result.ok(clientPage);
    }
}
