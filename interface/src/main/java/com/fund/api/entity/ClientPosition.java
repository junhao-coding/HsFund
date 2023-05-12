package com.fund.api.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
/**
* 
* @TableName client_position
*/
@Data
public class ClientPosition implements Serializable {
    /**
     * 由雪花算法生成
     */
    private Long positionId;

    @NotNull(message="基金代码不能为空")
    private Integer productId;

    @NotNull(message="客户账号不能为空")
    private Integer clientId;

    @NotNull(message="银行卡号不能为空")
    private String cardId;

    @NotNull(message="风险等级是否匹配不能为空")
    private Boolean riskMismatch;

    @NotNull(message="持有份额不能为空")
    private BigDecimal portion;

    private Date createTime;

    private Date updateTime;
}
