package com.fund.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetWorthDTO {
    private String productId;
    private BigDecimal netWorth;
    private BigDecimal portion;
    private BigDecimal netWorthPer;
}
