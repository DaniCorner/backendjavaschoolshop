package com.javaschoolshop;

import com.javaschoolshop.controller.OrderController;
import com.javaschoolshop.entity.Order;
import com.javaschoolshop.service.OrderService;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .build();
    }

    @Test
    public void testGetAllOrders() throws Exception {
        // Prepare test data
        Order order1 = new Order();
        order1.setId(1L);
        Order order2 = new Order();
        order2.setId(2L);
        List<Order> expectedOrders = Arrays.asList(order1, order2);

        // Mock the behavior of the OrderService
        when(orderService.getAllOrders()).thenReturn(expectedOrders);

        // Perform the API request
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        // Verify the interaction with the OrderService
        Mockito.verify(orderService, Mockito.times(1)).getAllOrders();
    }

    @Test
    public void testGetOrderById() throws Exception {
        // Prepare test data
        Order expectedOrder = new Order();
        expectedOrder.setId(1L);

        // Mock the behavior of the OrderService
        when(orderService.getOrderById(1L)).thenReturn(expectedOrder);

        // Perform the API request
        mockMvc.perform(get("/orders/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        // Verify the interaction with the OrderService
        Mockito.verify(orderService, Mockito.times(1)).getOrderById(1L);
    }

    @Test
    public void testGetOrderById_NotFound() throws Exception {
        // Prepare test data
        Order expectedOrder = null;

        // Mock the behavior of the OrderService
        when(orderService.getOrderById(1L)).thenReturn(expectedOrder);

        // Perform the API request
        mockMvc.perform(get("/orders/{id}", 1L))
                .andExpect(status().isNotFound());

        // Verify the interaction with the OrderService
        Mockito.verify(orderService, Mockito.times(1)).getOrderById(1L);
    }

    @Test
    public void testUpdateOrderStatus() throws Exception {
        // Prepare test data
        Order expectedOrder = new Order();
        expectedOrder.setId(1L);
        expectedOrder.setStatus("Shipped");

        String newStatus = "Shipped";

        // Mock the behavior of the OrderService
        when(orderService.updateOrderStatus(1L, newStatus)).thenReturn(expectedOrder);

        // Perform the API request
        mockMvc.perform(put("/orders/{id}/status", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStatus))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("Shipped"));

        // Verify the interaction with the OrderService
        Mockito.verify(orderService, Mockito.times(1)).updateOrderStatus(1L, newStatus);
    }

    @Test
    public void testUpdateOrderStatus_NotFound() throws Exception {
        // Prepare test data
        Order expectedOrder = null;

        String newStatus = "Shipped";

        // Mock the behavior of the OrderService
        when(orderService.updateOrderStatus(1L, newStatus)).thenReturn(expectedOrder);

        // Perform the API request
        mockMvc.perform(put("/orders/{id}/status", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStatus))
                .andExpect(status().isNotFound());

        // Verify the interaction with the OrderService
        Mockito.verify(orderService, Mockito.times(1)).updateOrderStatus(1L, newStatus);
    }
}