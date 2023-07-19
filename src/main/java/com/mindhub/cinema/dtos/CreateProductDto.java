package com.mindhub.cinema.dtos;

import com.mindhub.cinema.utils.enums.ProductType;

public class CreateProductDto {

    private String productName;

    private Double productPrice;

    private Integer stock;

    private ProductType productType;

    private Integer net_content;


    public CreateProductDto(String productName, Double productPrice, Integer stock, ProductType productType, Integer net_content) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.stock = stock;
        this.productType = productType;
        this.net_content = net_content;
    }

    public String getProductName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public ProductType getProductType() {
        return productType;
    }

    public Integer getNet_content() {
        return net_content;
    }
}
