package com.javaschoolshop.service;

import com.javaschoolshop.dto.Purchase;
import com.javaschoolshop.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
