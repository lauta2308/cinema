package com.mindhub.cinema.dtos.models_dtos;

import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.utils.enums.ProductType;

public class ProductDto {

    private long id;

    private String name;

    private Double productPrice;



    private ProductType productType;

    private Integer net_content;


    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.productPrice = product.getProductPrice();

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



    public ProductType getProductType() {
        return productType;
    }

    public Integer getNet_content() {
        return net_content;
    }

}
