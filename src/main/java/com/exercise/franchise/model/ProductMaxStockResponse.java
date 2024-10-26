package com.exercise.franchise.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductMaxStockResponse {

    private Long productId;
    private String productName;
    private int stock;
    private String branchName;

    public ProductMaxStockResponse(Long productId, String productName, int stock, String branchName) {
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
        this.branchName = branchName;
    }
}
