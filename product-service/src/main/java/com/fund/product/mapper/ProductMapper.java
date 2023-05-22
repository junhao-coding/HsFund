package com.fund.product.mapper;

import com.fund.api.dto.NetWorthDto;
import com.fund.api.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
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

    List<NetWorthDto> getNetWorthDto();

    void updateNetWorthBatch(List<NetWorthDto> list);

    @Update("update product set net_worth_old = net_worth")
    void updateNetWorthOld();
}
