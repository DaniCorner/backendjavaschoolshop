package com.javaschoolshop.dto;

import lombok.Data;

@Data
public class ProductQuantityDTO {
    private Long productId;
    private String imageUrl;
    private Long totalQuantity;
    public ProductQuantityDTO(Long productId, String imageUrl, Long totalQuantity) {
        this.productId = productId;
        this.totalQuantity = totalQuantity;
        this.imageUrl = imageUrl;
    }
}
