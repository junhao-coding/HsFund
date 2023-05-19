package com.fund.business.handler;

import com.fund.api.dto.Result;
import com.fund.business.exception.BalanceNotEnoughException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class BusinessExceptionHandler {

    @ExceptionHandler(BalanceNotEnoughException.class)
    public Result balanceExceptionHandler(BalanceNotEnoughException ex){
        return Result.fail(ex.getMessage());
    }

}
