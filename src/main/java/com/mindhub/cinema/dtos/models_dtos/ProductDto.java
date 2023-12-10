package com.mindhub.cinema.dtos.models_dtos;

import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.utils.enums.ProductType;

public class ProductDto {

    private long id;

    private String name;

    private Double productPrice;



    private ProductType productType;

    private Integer net_content;

    private Integer times_sold;

    private Boolean available;


    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.productPrice = product.getProductPrice();

        this.productType = product.getProductType();
        this.net_content = product.getNet_content();
        this.times_sold = product.getTimes_sold();
        this.available = product.getAvailable();
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

    public Integer getTimes_sold() {
        return times_sold;
    }

    public Boolean getAvailable() {
        return available;
    }
}
