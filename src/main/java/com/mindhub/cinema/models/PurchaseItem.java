package com.mindhub.cinema.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PurchaseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public PurchaseItem() {
    }

    public PurchaseItem(Integer quantity, Purchase purchase, Product product) {
        this.quantity = quantity;
        this.purchase = purchase;
        this.product = product;
    }

    public long getId() {
        return id;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
