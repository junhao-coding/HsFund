package com.fund.client.controller;

import com.fund.api.dto.Result;
import com.fund.api.service.TestService;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/10  10:16
 */
@RestController
@RequestMapping("/test")
public class  TestController {
    @CloudReference
    private TestService testService;

    @GetMapping("/{id}")
    public Result getClientById(@PathVariable("id") int id){
        return Result.ok(testService.getClientById(id));
    }
}
