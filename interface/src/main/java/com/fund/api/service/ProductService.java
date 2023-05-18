package com.fund.api.service;


import com.fund.api.dto.Page;
import com.fund.api.entity.Product;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.util.List;
import java.util.Map;


@CloudService
public interface ProductService {

    void addProduct(Product product);

    Product getProductById(String id);

    void updateProduct(Product product);

    void deleteProductById(String id);

     Page<Product> getProductByPage(Integer page, Integer pageSize);

    List<Map<String,Object>> getProductLikely(String s);


}
