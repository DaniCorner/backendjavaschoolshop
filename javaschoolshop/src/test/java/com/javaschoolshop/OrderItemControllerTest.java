package com.javaschoolshop;

import com.javaschoolshop.controller.OrderItemController;
import com.javaschoolshop.entity.OrderItem;
import com.javaschoolshop.service.OrderItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderItemControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderItemService orderItemService;

    @InjectMocks
    private OrderItemController orderItemController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderItemController)
                .build();
    }

    @Test
    public void testGetProductQuantitiesOrderedByTotalQuantity() throws Exception {
        // Prepare test data
        Object[] result1 = {1L, "image1.jpg", 10L};
        Object[] result2 = {2L, "image2.jpg", 8L};
        List<Object[]> expectedProductQuantities = Arrays.asList(result1, result2);

        // Mock the behavior of the OrderItemService
        when(orderItemService.getProductQuantitiesOrderedByTotalQuantity()).thenReturn(expectedProductQuantities);

        // Perform the API request
        mockMvc.perform(get("/order-items/product-quantities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[0].imageUrl").value("image1.jpg"))
                .andExpect(jsonPath("$[0].totalQuantity").value(10))
                .andExpect(jsonPath("$[1].productId").value(2))
                .andExpect(jsonPath("$[1].imageUrl").value("image2.jpg"))
                .andExpect(jsonPath("$[1].totalQuantity").value(8));

        // Verify the interaction with the OrderItemService
        Mockito.verify(orderItemService, Mockito.times(1)).getProductQuantitiesOrderedByTotalQuantity();
    }

    @Test
    public void testGetOrderItemsByUsername() throws Exception {
        // Prepare test data
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setId(1L);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setId(2L);
        List<OrderItem> expectedOrderItems = Arrays.asList(orderItem1, orderItem2);

        // Mock the behavior of the OrderItemService
        when(orderItemService.getOrderItemsByUsername("username")).thenReturn(expectedOrderItems);

        // Perform the API request
        mockMvc.perform(get("/order-items/username/{username}", "username"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        // Verify the interaction with the OrderItemService
        Mockito.verify(orderItemService, Mockito.times(1)).getOrderItemsByUsername("username");
    }

}
