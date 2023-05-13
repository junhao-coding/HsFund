package com.fund.product.impl;

import com.fund.api.dto.Result;
import com.fund.api.entity.Product;
import com.fund.api.service.ProductService;
import com.fund.product.exception.ProductException;
import com.fund.product.mapper.ProductMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@CloudComponent
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Override
    public void addProduct(Product product) {
        Product product1 = productMapper.selectById(product.getProductId());
        if(!Objects.isNull(product1)) throw new ProductException("产品编号已存在");
        Product product2 = productMapper.selectByName(product.getProductName());
        if(!Objects.isNull(product2)) throw new ProductException("产品名称已存在");
        productMapper.insertProduct(product);
    }

    @Override
    public Product getProductById(String id) {
        return productMapper.selectById(id);
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    @Override
    public void deleteProductById(String id) {
        productMapper.deleteProductById(id);
    }

    @Override
    public Result getProductByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Product> products = productMapper.getAllProduct();
        PageInfo<Product> productPageInfo = new PageInfo<>(products);
        return Result.ok(products,productPageInfo.getTotal());
    }
}
