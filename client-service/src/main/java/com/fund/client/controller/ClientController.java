package com.fund.client.controller;

import com.fund.api.dto.Page;
import com.fund.api.dto.Result;
import com.fund.api.entity.Client;
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

    @GetMapping("/{id}")
    public Result getClientById(@PathVariable("id") int id){
        Client client = clientService.selectClientById(id);
        if(client == null){
            return Result.fail("不存在该用户");
        }
        return Result.ok(client);
    }

    @GetMapping
    public Result getNameLikely(@RequestParam("name") String name){
        String likeName = name + "%";
        List<String> nameList = clientService.selectClientByLikeName(likeName);
        return Result.ok(nameList);
    }

    @PostMapping()
    public Result addClient(@RequestBody @Valid Client client){
        List<String> nameList = clientService.selectClientByLikeName(client.getClientName());
        if(nameList.size() != 0){
            return Result.fail("用户名已存在");
        }
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
        Page<Client> clientPage = clientService.selectClientByPage(pageNum, pageSize);
        return Result.ok(clientPage);
    }
}
