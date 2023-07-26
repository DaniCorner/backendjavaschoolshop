package com.javaschoolshop.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface DatesService {
    List<Object[]> getRevenueStats();
}