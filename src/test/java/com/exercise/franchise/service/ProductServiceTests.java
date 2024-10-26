package com.exercise.franchise.service;

import com.exercise.franchise.model.Branch;
import com.exercise.franchise.model.Product;
import com.exercise.franchise.repository.BranchRepository;
import com.exercise.franchise.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private ProductService productService;

    private Branch branch;
    private Product product;

    @BeforeEach
    public void setUp() {
        branch = new Branch();
        branch.setId(1L);
        branch.setName("Branch 1");

        product = new Product();
        product.setId(1L);
        product.setName("Product 1");
    }

    @Test
    public void testAddProduct() {
        Long branchId = 1L;

        when(branchRepository.findById(branchId)).thenReturn(Optional.of(branch));

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.addProduct(branchId, product);

        assertNotNull(savedProduct);
        assertEquals("Product 1", savedProduct.getName());
        assertEquals(branch, savedProduct.getBranch());
    }

    @Test
    public void testGetProductById() {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(productId);

        assertTrue(foundProduct.isPresent());
        assertEquals("Product 1", foundProduct.get().getName());
    }

    @Test
    public void testAddProduct_BranchNotFound() {
        Long branchId = 2L;

        when(branchRepository.findById(branchId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> productService.addProduct(branchId, product));
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testUpdateStock() {
        Long productId = 1L;
        int newStock = 50;

        Product product = new Product();
        product.setId(productId);
        product.setStock(20);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = productService.updateStock(productId, newStock);

        assertNotNull(updatedProduct);
        assertEquals(newStock, updatedProduct.getStock());
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateStock_ProductNotFound() {
        Long productId = 1L;
        int newStock = 50;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.updateStock(productId, newStock);
        });

        assertEquals("Product not found", exception.getMessage());
        verify(productRepository, never()).save(any());
    }
}