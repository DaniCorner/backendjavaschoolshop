package com.javaschoolshop.service;

import com.javaschoolshop.dao.CustomerRepository;
import com.javaschoolshop.dto.Purchase;
import com.javaschoolshop.dto.PurchaseResponse;
import com.javaschoolshop.entity.Customer;
import com.javaschoolshop.entity.Order;
import com.javaschoolshop.entity.OrderItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private CustomerRepository customerRepository;
    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    @Transactional //Single unit of work, if something fails, the whole transaction is rolled back
    public PurchaseResponse placeOrder(Purchase purchase) {
        Order order = purchase.getOrder(); //retrieves order from DTO

        String orderTrackingNumber = generateOrderTrackingNumber(); //generate OTN
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems(); //populate order with OrderItems, set in case of more +1 same movie
        orderItems.forEach(item -> order.add(item)); //.add aspcoates Item to the Order

        order.setBillingAddress(purchase.getBillingAddress()); //populate order with BA&SA
        order.setShippingAddress(purchase.getShippingAddress());

        order.setStatus("Not Shipped");

        Customer customer = purchase.getCustomer(); //populate customer with order
        customer.add(order);

        customerRepository.save(customer); //save to the database

        return new PurchaseResponse(orderTrackingNumber);
    }
    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}









