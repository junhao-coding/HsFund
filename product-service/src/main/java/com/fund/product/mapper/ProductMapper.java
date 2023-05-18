package com.fund.product.mapper;

import com.fund.api.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    Product selectByName(String name);
    Product selectById(String id);

    void insertProduct(Product product);

    void updateProduct(Product product);

    void deleteProductById(String id);

    List<Product> getAllProduct();

    /**
     * 模糊查询查询产品id和名字
     */
    List<Map<String, Object>> selectProductLikely(String option);


}
