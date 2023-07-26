package com.javaschoolshop;

import com.javaschoolshop.controller.AddressController;
import com.javaschoolshop.dto.AddressCountDTO;
import com.javaschoolshop.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @Test
    public void testGetCountriesWithMostOccurrences() {
        AddressCountDTO country1 = new AddressCountDTO("Spain", 10L);
        AddressCountDTO country2 = new AddressCountDTO("Germany", 8L);
        List<AddressCountDTO> expectedCountries = Arrays.asList(country1, country2);

        when(addressService.getCountriesWithMostOccurrences()).thenReturn(expectedCountries);

        List<AddressCountDTO> result = addressController.getCountriesWithMostOccurrences();

        assertThat(result).isEqualTo(expectedCountries);
    }
}