package com.javaschoolshop.service;

import com.javaschoolshop.dto.AddressCountDTO;

import java.util.List;

public interface AddressService {
    List<AddressCountDTO> getCountriesWithMostOccurrences();
}
