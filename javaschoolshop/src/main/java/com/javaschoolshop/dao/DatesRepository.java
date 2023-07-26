package com.javaschoolshop.dao;

import com.javaschoolshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatesRepository extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = "SELECT 'Weeks Revenues' AS category, " +
            "SUM(CASE WHEN date_created BETWEEN CURDATE() - INTERVAL WEEKDAY(CURDATE()) + 1 DAY AND CURDATE() + INTERVAL 1 DAY " +
            "THEN total_price ELSE 0 END) AS total_sum FROM orders " + //ELSE 0= if not, 0
            "UNION ALL " +
            "SELECT 'Monthly Revenues' AS category, " +
            "SUM(CASE WHEN date_created BETWEEN LAST_DAY(CURDATE() - INTERVAL 1 MONTH) + INTERVAL 1 DAY AND LAST_DAY(CURDATE()) " +
            "THEN total_price ELSE 0 END) AS total_sum FROM orders")
    List<Object[]> getRevenueStats();
}