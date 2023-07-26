package com.javaschoolshop.dao;

import com.javaschoolshop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.*;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("SELECT oi.productId, oi.imageUrl, SUM(oi.quantity) AS totalQuantity " +
            "FROM OrderItem oi " +
            "GROUP BY oi.productId, oi.imageUrl " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findProductQuantitiesOrderByTotalQuantityDesc();

    @Query("SELECT oi FROM OrderItem oi JOIN oi.order o JOIN o.customer c WHERE c.firstName = :username")
    List<OrderItem> getOrderItemsByUsername(@Param("username") String username);



    /*SELECT oi.product_id, oi.image_url, SUM(oi.quantity) AS totalQuantity
    FROM order_item oi
    GROUP BY oi.product_id, oi.image_url
    ORDER BY totalQuantity DESC;*/

    /*SELECT oi.*
    FROM order_item oi
    JOIN orders o ON oi.order_id = o.id
    JOIN customer c ON o.customer_id = c.id
    WHERE c.first_name = 'Morrissey';*/
}
