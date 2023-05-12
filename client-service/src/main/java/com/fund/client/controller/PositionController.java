package com.fund.client.controller;

import com.fund.api.dto.Result;
import com.fund.api.entity.ClientPosition;
import com.fund.api.service.PositionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
    public Result updatePosition(@Param("positionId") long positionId,
                                 @Param("changePosition") String changePosition){
        positionService.updatePosition(positionId, new BigDecimal(changePosition));
        return Result.ok();
    }
}
