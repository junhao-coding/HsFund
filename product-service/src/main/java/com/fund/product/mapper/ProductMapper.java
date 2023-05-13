package com.fund.product.mapper;

import com.fund.api.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    Product selectByName(String name);
    Product selectById(String id);

    void insertProduct(Product product);

    void updateProduct(Product product);

    void deleteProductById(String id);

    List<Product> getAllProduct();
}
