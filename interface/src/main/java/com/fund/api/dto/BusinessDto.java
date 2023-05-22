package com.fund.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/22  10:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDto {
    private Integer clientId;
    private String productId;
    private String tradeType;
    private String cardId;
    private BigDecimal tradePortion;
    private BigDecimal tradeAmount;
}
