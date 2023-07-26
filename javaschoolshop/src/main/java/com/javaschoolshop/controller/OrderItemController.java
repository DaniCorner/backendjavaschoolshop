package com.javaschoolshop.controller;

import com.javaschoolshop.dto.ProductQuantityDTO;
import com.javaschoolshop.entity.OrderItem;
import com.javaschoolshop.service.OrderItemService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Data
@RestController
@RequestMapping("/order-items")
public class OrderItemController {
        private final OrderItemService orderItemService;
        @Autowired
        public OrderItemController(OrderItemService orderItemService) {
            this.orderItemService = orderItemService;
        }
        @GetMapping("/product-quantities")
        public List<ProductQuantityDTO> getProductQuantitiesOrderedByTotalQuantity() {
            List<Object[]> productQuantities = orderItemService.getProductQuantitiesOrderedByTotalQuantity();
            List<ProductQuantityDTO> dtos = new ArrayList<>();

            for (Object[] result : productQuantities) {
                Long productId = (Long) result[0];
                String imageUrl = (String) result[1];
                Long totalQuantity = (Long) result[2];
                ProductQuantityDTO dto = new ProductQuantityDTO(productId, imageUrl, totalQuantity);
                dtos.add(dto);
            }
            return dtos;
        }

    @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderItem>> getOrderItemsByUsername(@PathVariable("username") String username) {
        List<OrderItem> orderItems = orderItemService.getOrderItemsByUsername(username);
        return ResponseEntity.ok(orderItems);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String handleMediaTypeNotAcceptable(
            final HttpMediaTypeNotAcceptableException exception) {
        return "Acceptable MIME type: " + MediaType.APPLICATION_JSON_VALUE;
    }
}

