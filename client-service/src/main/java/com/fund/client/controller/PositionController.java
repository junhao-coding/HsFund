package com.fund.client.controller;

import com.fund.api.dto.Result;
import com.fund.api.entity.ClientPosition;
import com.fund.api.service.PositionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  16:31
 */
@RestController
@RequestMapping("/fund/position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @PostMapping
    public Result addPosition(@RequestBody ClientPosition position){
        positionService.addPosition(position);
        return Result.ok();
    }

    @PutMapping
    public Result updatePosition(@RequestParam("positionId") long positionId,
                                 @RequestParam("changePosition") String changePosition){
        positionService.updatePosition(positionId, new BigDecimal(changePosition));
        return Result.ok();
    }

    @GetMapping("/{clientId}")
    public Result getPositionsByClientId(@PathVariable int clientId){
        List<ClientPosition> positionList = positionService.getPositionsByClientId(clientId);
        if(positionList == null){
            return Result.fail("该用户当前未建仓");
        }
        return Result.ok();
    }

    @GetMapping("/{positionId}")
    public Result getOrdersByPositionId(@RequestParam("year") int year,
                                        @RequestParam("month") int month,
                                        @RequestParam("positionId") long positionId){
        positionService.getOrdersByPositionId(year, month, positionId);
        return Result.ok();
    }
}
