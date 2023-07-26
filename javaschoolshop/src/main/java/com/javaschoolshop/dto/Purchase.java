package com.javaschoolshop.dto;

import com.javaschoolshop.entity.Address;
import com.javaschoolshop.entity.Customer;
import com.javaschoolshop.entity.Order;
import com.javaschoolshop.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
