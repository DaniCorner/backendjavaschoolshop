package com.javaschoolshop.controller;

import com.javaschoolshop.entity.ProductCategory;
import com.javaschoolshop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @PostMapping
    public ProductCategory addProductCategory(@RequestBody ProductCategory productCategory) {
        return productCategoryService.addProductCategory(productCategory);
    }
    @PutMapping("/{id}")
    public ProductCategory updateProductCategory(@PathVariable Long id, @RequestBody ProductCategory productCategory) {
        return productCategoryService.updateProductCategory(id, productCategory);
    }
    @DeleteMapping("/{id}")
    public void deleteProductCategory(@PathVariable Long id) {
        productCategoryService.deleteProductCategory(id);
    }
}