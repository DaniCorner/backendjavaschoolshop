package com.javaschoolshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschoolshop.controller.ProductController;
import com.javaschoolshop.entity.Product;
import com.javaschoolshop.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // Prepare test data
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        // Mock the behavior of the ProductService
        Mockito.when(productService.getAllProducts()).thenReturn(products);

        // Perform the API request
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Product 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Product 2"));

        // Verify the interaction with the ProductService
        Mockito.verify(productService, Mockito.times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() throws Exception {
        // Prepare test data
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product 1");

        // Mock the behavior of the ProductService
        Mockito.when(productService.getProductById(1L)).thenReturn(product);

        // Perform the API request
        mockMvc.perform(get("/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Product 1"));

        // Verify the interaction with the ProductService
        Mockito.verify(productService, Mockito.times(1)).getProductById(1L);
    }

    @Test
    public void testGetProductById_NotFound() throws Exception {
        // Mock the behavior of the ProductService
        Mockito.when(productService.getProductById(1L)).thenReturn(null);

        // Perform the API request
        mockMvc.perform(get("/products/{id}", 1L))
                .andExpect(status().isNotFound());

        // Verify the interaction with the ProductService
        Mockito.verify(productService, Mockito.times(1)).getProductById(1L);
    }


    @Test
    public void testDeleteProduct() throws Exception {
        // Perform the API request
        mockMvc.perform(delete("/products/{id}", 1L))
                .andExpect(status().isNoContent());

        // Verify the interaction with the ProductService
        Mockito.verify(productService, Mockito.times(1)).deleteProduct(1L);
    }

    @Test
    public void testGetSimilarMovies() throws Exception {
        // Prepare test data
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");

        List<Product> similarMovies = new ArrayList<>();
        similarMovies.add(product1);
        similarMovies.add(product2);

        // Mock the behavior of the ProductService
        Mockito.when(productService.findRandomSimilarMovies(1L)).thenReturn(similarMovies);

        // Perform the API request
        mockMvc.perform(get("/products/{id}/similar", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Product 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Product 2"));

        // Verify the interaction with the ProductService
        Mockito.verify(productService, Mockito.times(1)).findRandomSimilarMovies(1L);
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
