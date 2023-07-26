package com.javaschoolshop.controller;

import com.javaschoolshop.dto.AddressCountDTO;
import com.javaschoolshop.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @GetMapping("/countries-with-most-occurrences")
    public List<AddressCountDTO> getCountriesWithMostOccurrences() {
        return addressService.getCountriesWithMostOccurrences();
    }
}