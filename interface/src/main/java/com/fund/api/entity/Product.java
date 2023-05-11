package com.fund.api.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName product
*/
public class Product implements Serializable {

    /**
     *
     */
    @NotNull(message = "[]不能为空")
    private Integer productId;
    /**
     *
     */
    @NotBlank(message = "[]不能为空")
    @Size(max = 32, message = "编码长度不能超过32")
    @Length(max = 32, message = "编码长度不能超过32")
    private String productName;
    /**
     *
     */
    @NotNull(message = "[]不能为空")
    private BigDecimal netWorth;
    /**
     *
     */
    @NotNull(message = "[]不能为空")
    private BigDecimal netWorthOld;
    /**
     *
     */
    @NotNull(message = "[]不能为空")
    private BigDecimal portion;
    /**
     *
     */
    @NotBlank(message = "[]不能为空")
    @Size(max = 3, message = "编码长度不能超过3")
    @Length(max = 3, message = "编码长度不能超过3")
    private String productType;
    /**
     *
     */
    @NotNull(message = "[]不能为空")
    private Integer riskLevel;
    /**
     *
     */
    @NotNull(message = "[]不能为空")
    private Boolean purchaseState;
    /**
     *
     */
    @NotNull(message = "[]不能为空")
    private Boolean sellState;
    /**
     *
     */
    @NotNull(message = "[]不能为空")
    private Date setDate;
    /**
     *
     */
    @NotBlank(message = "[]不能为空")
    @Size(max = 16, message = "编码长度不能超过16")
    @Length(max = 16, message = "编码长度不能超过16")
    private String productAdmin;
    /**
     *
     */
    @NotBlank(message = "[]不能为空")
    @Size(max = 8, message = "编码长度不能超过8")
    @Length(max = 8, message = "编码长度不能超过8")
    private String productManager;
    /**
     *
     */
    @NotBlank(message = "[]不能为空")
    @Size(max = 16, message = "编码长度不能超过16")
    @Length(max = 16, message = "编码长度不能超过16")
    private String productCustodian;
}