package com.fund.api.entity;

import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
* 
* @TableName position_order
*/
@Data
public class PositionOrder implements Serializable {

    /**
     * 由雪花算法生成
     */
    private Long positionOrderId;

    @NotNull(message="持仓编号不能为空")
    private Long positionId;

    /**
     * 这里的份额是一次基金申购赎回产生的份额变化
     */
    @NotNull(message="份额不能为空")
    private BigDecimal portion;

    private Date createTime;
}
