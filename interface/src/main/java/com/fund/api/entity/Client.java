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
    private Integer clientId;
    /**
    * 
    */
    @NotBlank(message="客户姓名不能为空")
    @Length(max= 8,message="编码长度不能超过8")
    private String clientName;
    /**
    * 
    */
    @NotBlank(message="客户类型不能为空")
    @Length(max= 8,message="编码长度不能超过8")
    private String clientType;
    /**
    * 
    */
    @NotBlank(message="身份证类型不能为空")
    @Length(max= 8,message="编码长度不能超过8")
    private String cardType;
    /**
    * 
    */
    private String cardNumber;
    /**
    * 
    */
    @NotBlank(message="性别不能为空")
    @Length(max= 1,message="编码长度不能超过1")
    private String sex;
    /**
    * 
    */
    @NotNull(message="年龄不能为空")
    private Integer age;
    /**
    * 
    */
    @NotNull(message="联系电话不能为空")
    private String phoneNumber;
    /**
    * 
    */
    @NotNull(message="风险等级不能为空")
    private Integer riskLevel;
    /**
    * 
    */
    private Date createTime;

}
