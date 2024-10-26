package com.exercise.franchise.controller;

import com.exercise.franchise.model.Product;
import com.exercise.franchise.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAddProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("New Product");

        Long branchId = 1L;

        given(productService.addProduct(Mockito.any(Long.class), Mockito.any(Product.class))).willReturn(product);

        String productJson = "{ \"name\": \"New Product\" }";

        mockMvc.perform(post("/products/{branchId}", branchId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Product"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Long productId = 1L;

        mockMvc.perform(delete("/products/{id}", productId))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).deleteProduct(productId);
    }
}
