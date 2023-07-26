package com.javaschoolshop.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressCountDTO { // DTO, attributes from (or not) different sources
    private String country;
    private Long countryCount;
    @JsonCreator
    public AddressCountDTO(@JsonProperty("country") String country, @JsonProperty("countryCount") Long countryCount) {
        this.country = country;
        this.countryCount = countryCount;
    }
    public String getCountry() {
        return country;
    }
    public Long getCountryCount() {
        return countryCount;
    }
}

