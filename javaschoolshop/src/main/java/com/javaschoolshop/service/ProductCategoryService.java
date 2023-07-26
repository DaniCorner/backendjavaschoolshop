package com.javaschoolshop.service;

import com.javaschoolshop.entity.ProductCategory;

import java.awt.*;

public interface ProductCategoryService {

    ProductCategory getProductCategoryById(Long id);
    ProductCategory addProductCategory(ProductCategory productCategory);
    ProductCategory updateProductCategory(Long id, ProductCategory productCategory);
    void deleteProductCategory(Long id);
}
