package com.fund.product.controller;

import com.fund.api.dto.Result;
import com.fund.api.entity.Product;
import com.fund.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fund")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public Result addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return Result.ok();
    }

    @GetMapping("/product/{id}")
    public Result getProductById(@PathVariable String id){
        Product product = productService.getProductById(id);
        return Result.ok(product);
    }

    @PutMapping("/product")
    public Result updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
        return Result.ok();
    }

    @DeleteMapping("/product/{id}")
    public  Result removeProductById(@PathVariable String id){
        productService.deleteProductById(id);
        return Result.ok();
    }

    @GetMapping("/product/page")
    public Result getProductByPage(Integer pageNum,Integer pageSize){
        return productService.getProductByPage(pageNum,pageSize);
    }

}
