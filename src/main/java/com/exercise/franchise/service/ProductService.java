package com.exercise.franchise.service;

import com.exercise.franchise.model.Branch;
import com.exercise.franchise.model.Product;
import com.exercise.franchise.repository.BranchRepository;
import com.exercise.franchise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BranchRepository branchRepository;

    public Product addProduct(Long branchId, Product product) {
        Branch branch = branchRepository.findById(branchId).orElseThrow();
        product.setBranch(branch);
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
