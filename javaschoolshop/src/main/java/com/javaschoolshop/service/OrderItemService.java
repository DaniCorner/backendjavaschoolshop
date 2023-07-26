package com.javaschoolshop.service;

import com.javaschoolshop.entity.OrderItem;

import java.awt.*;
import java.util.List;

public interface OrderItemService {
    List<Object[]> getProductQuantitiesOrderedByTotalQuantity();


    List<OrderItem> getOrderItemsByUsername(String username);

}