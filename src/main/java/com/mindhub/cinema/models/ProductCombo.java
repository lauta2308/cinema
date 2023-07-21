package com.mindhub.cinema.models;

import com.mindhub.cinema.utils.enums.ProductComboType;
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

    private double comboDefaultPrice = 0.0;

    private double comboFinalPrice = 0.0;

    private boolean isTemplateCombo = false;

    private ProductComboType productComboType;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getComboDefaultPrice() {
        return comboDefaultPrice;
    }

    public void setComboDefaultPrice(double comboDefaultPrice) {
        this.comboDefaultPrice = comboDefaultPrice;
    }

    public double getComboFinalPrice() {
        return comboFinalPrice;
    }

    public void setComboFinalPrice(double comboFinalPrice) {
        this.comboFinalPrice = comboFinalPrice;
    }

    public boolean isTemplateCombo() {
        return isTemplateCombo;
    }

    public void setTemplateCombo(boolean templateCombo) {
        isTemplateCombo = templateCombo;
    }

    public ProductComboType getProductComboType() {
        return productComboType;
    }

    public void setProductComboType(ProductComboType productComboType) {
        this.productComboType = productComboType;
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


    public void updateComboPrice(){

        if(this.productComboType == ProductComboType.SINGLE){
                this.comboFinalPrice = this.comboDefaultPrice - (this.comboDefaultPrice * 0.05);

        } else if (this.productComboType == ProductComboType.SINGLE_MEGA) {

            this.comboFinalPrice = this.comboDefaultPrice - (this.comboDefaultPrice * 0.10);
        } else {
            this.comboFinalPrice = this.comboDefaultPrice - (this.comboDefaultPrice * 0.10);
        }


    }
}
