package com.javaschoolshop.dao;

import com.javaschoolshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
    //SELECT * FROM product p WHERE p.title LIKE '%DIRECTOR: STEVEN SPIELBERG%'
    // OR p.parameters LIKE '%DIRECTOR: STEVEN SPIELBERG%';
    Page<Product> findByTitleContainingOrParametersContaining(
            @RequestParam("title") String title,
            @RequestParam("parameters") String parameters,
            Pageable pageable
    );

    //SELECT * FROM product p WHERE p.parameters LIKE '%COUNTRY: Japan%' AND p.id <> 5;
    List<Product> findByParametersContainingAndIdNot(String keyword, Long id);

     Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
     List<Product> findByParametersContaining(String keyword);
}
