package com.javaschoolshop.service;

import com.javaschoolshop.dao.ProductCategoryRepository;
import com.javaschoolshop.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public ProductCategory getProductCategoryById(Long id) {
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product category not found with id: " + id));
    }
    @Override
    public ProductCategory addProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
    @Override
    public ProductCategory updateProductCategory(Long id, ProductCategory productCategory) {
        ProductCategory existingProductCategory = getProductCategoryById(id);
        existingProductCategory.setCategoryName(productCategory.getCategoryName());
        return productCategoryRepository.save(existingProductCategory);
    }
    @Override
    public void deleteProductCategory(Long id) {
        ProductCategory productCategory = getProductCategoryById(id);
        productCategoryRepository.delete(productCategory);
    }
}
