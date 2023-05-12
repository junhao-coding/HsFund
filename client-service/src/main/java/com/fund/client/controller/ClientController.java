package com.fund.client.controller;

import com.fund.api.dto.Result;
import com.fund.api.entity.Client;
import com.fund.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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

    @GetMapping("/{id}")
    public Result getClientById(@PathVariable("id") int id){
        return Result.ok(clientService.selectClientById(id));
    }

    @PostMapping()
    public Result addClient(@RequestBody @Valid Client client){
        clientService.addClient(client);
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
        return Result.ok(clientService.selectClientByPage(pageNum, pageSize));
    }
}
