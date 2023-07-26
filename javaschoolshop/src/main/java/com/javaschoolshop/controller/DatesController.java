package com.javaschoolshop.controller;

import com.javaschoolshop.service.DatesService;
import com.javaschoolshop.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dates")
public class DatesController {
    private final DatesService datesService;
    public DatesController(DatesService datesService) {
        this.datesService = datesService;
    }
    @GetMapping("/revenue-stats")
    public List<Object[]> getRevenueStats() {
        return datesService.getRevenueStats();
    }
}
