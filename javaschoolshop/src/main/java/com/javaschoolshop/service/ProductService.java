package com.javaschoolshop.service;

import com.javaschoolshop.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProduct(Long id);


    List<Product> findRandomSimilarMovies(Long id);

}

