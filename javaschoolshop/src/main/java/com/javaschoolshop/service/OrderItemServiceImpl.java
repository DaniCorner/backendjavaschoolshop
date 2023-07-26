package com.javaschoolshop.service;

import com.javaschoolshop.dao.OrderItemRepository;
import com.javaschoolshop.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    @Override
    public List<Object[]> getProductQuantitiesOrderedByTotalQuantity() {
        return orderItemRepository.findProductQuantitiesOrderByTotalQuantityDesc();
    }
    @Override
    public List<OrderItem> getOrderItemsByUsername(String username) {
        return orderItemRepository.getOrderItemsByUsername(username);
    }
}
