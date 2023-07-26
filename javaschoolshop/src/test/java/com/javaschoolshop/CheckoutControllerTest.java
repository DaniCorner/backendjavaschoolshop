package com.javaschoolshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschoolshop.controller.CheckoutController;
import com.javaschoolshop.dto.Purchase;
import com.javaschoolshop.dto.PurchaseResponse;
import com.javaschoolshop.service.CheckoutService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CheckoutService checkoutService;

    @InjectMocks
    private CheckoutController checkoutController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(checkoutController)
                .build();
    }

    @Test
    public void testPlaceOrder() throws Exception {
        // Prepare test data
        Purchase purchase = new Purchase();
        // Set up the purchase data here

        PurchaseResponse expectedResponse = new PurchaseResponse("order123");

        // Mock the behavior of the CheckoutService
        Mockito.when(checkoutService.placeOrder(any(Purchase.class)))
                .thenReturn(expectedResponse);

        // Perform the API request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/checkout/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(purchase)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderTrackingNumber").value(expectedResponse.getOrderTrackingNumber()));

        // Verify the interaction with the CheckoutService
        verify(checkoutService, times(1)).placeOrder(any(Purchase.class));
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
