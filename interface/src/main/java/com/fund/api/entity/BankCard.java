package com.fund.api.entity;

import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
* 
* @TableName bank_card
*/
@Data
public class BankCard implements Serializable {

    private String cardId;

    @NotNull(message="客户账号不能为空")
    private Integer clientId;

    @NotNull(message="银行卡余额不能为空")
    private BigDecimal balance;

    private Date createTime;

    private Date updateTime;

}
