package com.fund.product.handler;

import com.fund.api.dto.Result;
import com.fund.product.exception.ProductException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public Result productExceptionHandler(ProductException ex){
        String message = ex.getMessage();
        System.out.println(message);
        return Result.fail(message);
    }
}
