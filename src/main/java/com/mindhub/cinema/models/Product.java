package com.mindhub.cinema.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name;

    private Double productPrice;

    private Integer stock;

    private ProductType productType;

    private Integer net_content;

    @OneToMany(mappedBy="product", fetch=FetchType.EAGER)
    private Set<PurchaseItem> productCombos = new HashSet<>();


    public Product() {
    }

    public Product(String name, Double productPrice, Integer stock, ProductType productType, Integer net_content) {
        this.name = name;
        this.productPrice = productPrice;
        this.stock = stock;
        this.productType = productType;
        this.net_content = net_content;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Integer getNet_content() {
        return net_content;
    }

    public void setNet_content(Integer net_content) {
        this.net_content = net_content;
    }

    public Set<PurchaseItem> getProductCombos() {
        return productCombos;
    }

    public void setProductCombos(Set<PurchaseItem> productCombos) {
        this.productCombos = productCombos;
    }
}
