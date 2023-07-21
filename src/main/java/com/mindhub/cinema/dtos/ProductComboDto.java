package com.mindhub.cinema.dtos;

import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.models.ProductCombo;
import com.mindhub.cinema.utils.enums.ProductComboType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductComboDto {


    private long id;

    private double comboDefaultPrice;

    private double comboFinalPrice;

    private boolean isTemplateCombo;

    private ProductComboType productComboType;

    private List<ProductDto> products;

    public ProductComboDto(ProductCombo productCombo) {
        this.id = productCombo.getId();
        this.comboDefaultPrice = productCombo.getComboDefaultPrice();
        this.comboFinalPrice = productCombo.getComboFinalPrice();
        this.isTemplateCombo = productCombo.isTemplateCombo();
        this.productComboType = productCombo.getProductComboType();
        this.products = productCombo.getProducts().stream().map(product -> new ProductDto(product)).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public double getComboDefaultPrice() {
        return comboDefaultPrice;
    }

    public double getComboFinalPrice() {
        return comboFinalPrice;
    }

    public boolean isTemplateCombo() {
        return isTemplateCombo;
    }

    public ProductComboType getProductComboType() {
        return productComboType;
    }

    public List<ProductDto> getProducts() {
        return products;
    }
}
