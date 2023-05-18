package com.fund.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/10  10:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean success;
    private String errorMsg;
    private Object data;

    public static Result ok(){
        return new Result(true, "", null);
    }
    public static Result ok(Object data){
        return new Result(true, "", data);
    }
    public static Result fail(String errorMsg){
        return new Result(false, errorMsg, null);
    }
}
