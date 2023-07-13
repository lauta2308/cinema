package com.mindhub.cinema.dtos;

import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.utils.enums.ProductType;

public class ProductDto {

    private long id;

    private String name;

    private Double productPrice;

    private Integer stock;

    private ProductType productType;

    private Integer net_content;




    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.productPrice = product.getProductPrice();
        this.stock = product.getStock();
        this.productType = product.getProductType();
        this.net_content = product.getNet_content();

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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
