package com.fund.api.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.math.BigDecimal;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Product implements Serializable {

    /**
     * 6位长数字
     */
    private String productId;
    /**
     *
     */
    @NotBlank(message = "产品名称不能为空")
    @Length(max = 32, message = "编码长度不能超过32")
    private String productName;
    /**
     *
     */
    @NotNull(message = "净值不能为空")
    private BigDecimal netWorth;
    /**
     *
     */
    @NotNull(message = "旧净值不能为空，第一次输入时可以用净值替代")
    private BigDecimal netWorthOld;
    /**
     *
     */
    @NotNull(message = "份额不能为空")
    private BigDecimal portion;
    /**
     *
     */
    @NotBlank(message = "产品类型不能为空")
    @Length(max = 3, message = "编码长度不能超过3")
    private String productType;
    /**
     *
     */
    @NotNull(message = "风险等级不能为空")
    //1（谨慎型），2（稳健型），3（平衡型），4（进取型），5（激进型）
    private Integer riskLevel;
    /**
     *
     */
    //0 暂停 1正常
    @NotNull(message = "申购状态不能为空")
    private Integer purchaseState;
    /**
     *
     */
    //0 暂停 1正常
    @NotNull(message = "赎回状态不能为空")
    private Integer sellState;
    /**
     *
     */
    //形如2021-10-21
    @NotNull(message = "成立日期不能为空")
    private String setDate;
    /**
     *
     */
    @NotBlank(message = "基金公司不能为空")
    @Length(max = 16, message = "编码长度不能超过16")
    private String productAdmin;
    /**
     *
     */
    @NotBlank(message = "基金经理不能为空")
    @Length(max = 8, message = "编码长度不能超过8")
    private String productManager;
    /**
     *
     */
    @NotBlank(message = "基金托管方不能为空")
    @Length(max = 16, message = "编码长度不能超过16")
    private String productCustodian;
}