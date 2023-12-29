package com.mindhub.cinema.dtos.models_dtos;
import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.models.ProductCombo;
import com.mindhub.cinema.models.Purchase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductComboDto {

    private long id;

    private String name;

    private double price = 0.0;


    private List<ProductDto> products;

    public ProductComboDto() {
    }

    public ProductComboDto(ProductCombo productCombo) {
        this.id = productCombo.getId();
        this.name = productCombo.getName();
        this.price = productCombo.getPrice();
        this.products = productCombo.getProducts().stream().map(product -> new ProductDto(product)).collect(Collectors.toList());
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<ProductDto> getProducts() {
        return products;
    }
}
