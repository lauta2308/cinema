package com.mindhub.cinema.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductCombo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name;

    private double price = 0.0;


    private boolean isTemplateCombo = false;


    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToMany
    @JoinTable(name = "purchase_product",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();


    public ProductCombo() {
    }


    public ProductCombo(String name, double price, boolean isTemplateCombo, List<Product> products) {
        this.name = name;
        this.price = price;
        this.isTemplateCombo = isTemplateCombo;
        this.products = products;
    }

    public long getId() {
        return id;
    }


    public boolean isTemplateCombo() {
        return isTemplateCombo;
    }

    public void setTemplateCombo(boolean templateCombo) {
        isTemplateCombo = templateCombo;
    }


    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
