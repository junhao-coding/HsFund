package com.fund.api.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
* 
* @TableName liquidation
*/
@Data
public class Liquidation implements Serializable {

    /**
     *
     */
    @NotNull(message = "清算编号不能为空")
    private Long liquidationId;
    /**
     *
     */
    @NotNull(message = "产品编号不能为空")
    private String productId;
    /**
     *
     */
    @NotNull(message = "单位净值不能为空")
    private BigDecimal netWorthPer;
    /**
     *
     */
    @NotNull(message = "清算日期不能为空")
    private LocalDate liquidationDate;
}