package com.fund.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class Business {
    private Long businessId;
    private Integer clientId;
    private String productId;
    private String tradeType;
    private String cardId;
    private BigDecimal tradePortion;
    private BigDecimal tradeAmount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expectedDay;
    private Boolean isConfirmed;
    private Boolean isWithdrawn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String businessDesc;
}
