package com.mindhub.cinema.dtos.param_dtos;

import org.springframework.web.bind.annotation.RequestParam;

public class AddPurchaseItemDto {

    private Long productId;

    private Integer productQuantity;

    private String productName;


    private Double productPrice;

    public AddPurchaseItemDto(Long productId, Integer productQuantity, String productName, Double productPrice) {
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }
}
