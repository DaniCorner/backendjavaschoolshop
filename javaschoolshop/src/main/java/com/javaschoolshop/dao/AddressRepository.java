package com.javaschoolshop.dao;

import com.javaschoolshop.dto.AddressCountDTO;
import com.javaschoolshop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a.country AS country, COUNT(a.country) AS countryCount FROM Address a GROUP BY a.country ORDER BY countryCount DESC")
    List<Object[]> findCountriesWithMostOccurrences();
}
