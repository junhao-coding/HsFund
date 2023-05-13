package com.fund.api.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

/**
* 
* @TableName card_order
*/
@Data
public class CardOrder implements Serializable {
    /**
     * 由雪花算法生成
     */
    private Long cardOrderId;

    @NotNull(message="银行卡号不能为空")
    private String cardId;

    @NotNull(message="交易金额不能为空")
    private BigDecimal orderAmount;

    private Date createTime;
}
