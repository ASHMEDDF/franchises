package com.exercise.franchise.repository;

import com.exercise.franchise.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.stock = (SELECT MAX(p2.stock) FROM Product p2 WHERE p2.branch = p.branch) AND p.branch.franchise.id = :franchiseId")
    List<Product> findProductsWithMaxStockPerBranch(@Param("franchiseId") Long franchiseId);
}
