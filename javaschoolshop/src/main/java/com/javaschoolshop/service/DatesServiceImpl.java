package com.javaschoolshop.service;

import com.javaschoolshop.dao.DatesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DatesServiceImpl implements DatesService {
    private final DatesRepository datesRepository;
    public DatesServiceImpl(DatesRepository datesRepository) {
        this.datesRepository = datesRepository;
    }
    @Override
    public List<Object[]> getRevenueStats() {
        return datesRepository.getRevenueStats();
    }
}
