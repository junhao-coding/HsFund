package com.fund.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fund.api.entity.Client;
import com.fund.api.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/23  14:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientProductDTO extends Client {
    //用户购买的所有产品信息
    private List<Product> productList;
}
