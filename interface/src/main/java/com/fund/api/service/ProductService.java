package com.fund.api.service;

import com.fund.api.dto.Result;
import com.fund.api.entity.Product;
import com.hundsun.jrescloud.rpc.annotation.CloudService;




@CloudService
public interface ProductService {

    void addProduct(Product product);

    Product getProductById(String id);

    void updateProduct(Product product);

    void deleteProductById(String id);

    Result getProductByPage(Integer page,Integer pageSize);




}
