package com.javaschoolshop.service;

import com.javaschoolshop.dao.AddressRepository;
import com.javaschoolshop.dto.AddressCountDTO;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    @Override
    public List<AddressCountDTO> getCountriesWithMostOccurrences() {
        List<Object[]> result = addressRepository.findCountriesWithMostOccurrences(); //Method custom query
        return convertToObjectList(result); //Retrieve a List<Object[]> with the info of 2 rows
    }
    private List<AddressCountDTO> convertToObjectList(List<Object[]> result) {
        List<AddressCountDTO> addressCountDTOs = new ArrayList<>();
        for (Object[] row : result) { //Loop each item in the result list
            String country = (String) row[0]; //Takes the info from each row
            Long countryCount = (Long) row[1];
            AddressCountDTO addressCountDTO = new AddressCountDTO(country, countryCount); //Create every ACDTO for each element
            addressCountDTOs.add(addressCountDTO); //Add them to a list ACDTOs
        }
        return addressCountDTOs;
    }
}