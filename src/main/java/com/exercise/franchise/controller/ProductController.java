package com.exercise.franchise.controller;

import com.exercise.franchise.model.Product;
import com.exercise.franchise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/{branchId}")
    public Product addProduct(@PathVariable Long branchId, @RequestBody Product product) {
        return productService.addProduct(branchId, product);
    }
}
