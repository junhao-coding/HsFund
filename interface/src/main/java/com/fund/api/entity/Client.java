package com.fund.api.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
* 
* @TableName client
*/
@Data
public class Client implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    private Integer clientId;
    /**
    * 
    */
    @NotBlank(message="[]不能为空")
    @Size(max= 8,message="编码长度不能超过8")
    @Length(max= 8,message="编码长度不能超过8")
    private String clientName;
    /**
    * 
    */
    @NotBlank(message="[]不能为空")
    @Size(max= 8,message="编码长度不能超过8")
    @Length(max= 8,message="编码长度不能超过8")
    private String clientType;
    /**
    * 
    */
    @NotBlank(message="[]不能为空")
    @Size(max= 8,message="编码长度不能超过8")
    @Length(max= 8,message="编码长度不能超过8")
    private String cardType;
    /**
    * 
    */
    private String cardNumber;
    /**
    * 
    */
    @NotBlank(message="[]不能为空")
    @Size(max= 1,message="编码长度不能超过1")
    @Length(max= 1,message="编码长度不能超过1")
    private String sex;
    /**
    * 
    */
    @NotNull(message="[]不能为空")
    private Integer age;
    /**
    * 
    */
    @NotNull(message="[]不能为空")
    private String phoneNumber;
    /**
    * 
    */
    @NotNull(message="[]不能为空")
    private Integer riskLevel;
    /**
    * 
    */
    @NotNull(message="[]不能为空")
    private Date createTime;

}
