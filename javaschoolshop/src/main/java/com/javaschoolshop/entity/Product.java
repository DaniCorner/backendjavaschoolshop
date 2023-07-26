package com.javaschoolshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "parameters")
    private String parameters;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    @JsonBackReference
    private ProductCategory category;

    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @Column(name = "weight")
    private int weight;

    @Column(name = "volume")
    private int volume;
}



