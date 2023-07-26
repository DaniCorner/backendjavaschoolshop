package com.javaschoolshop.service;

import com.javaschoolshop.dao.OrderRepository;
import com.javaschoolshop.entity.Order;
import com.javaschoolshop.entity.Product;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);

    Order updateOrderStatus(Long id, String status);


}