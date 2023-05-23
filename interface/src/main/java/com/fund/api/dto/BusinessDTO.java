package com.fund.api.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/22  10:54
 */
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fund.api.entity.Business;
import lombok.Data;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessDTO extends Business {
    private String clientName;
    private String productName;
}