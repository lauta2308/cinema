package com.mindhub.cinema.dtos.param_dtos;

import com.mindhub.cinema.dtos.models_dtos.ProductDto;

import java.util.List;

public class EditProductComboDto {

    private Long id;
    private String comboName;

    private Double comboPrice;

    private List<ProductDto> products;

    public EditProductComboDto() {
    }

    public EditProductComboDto(Long id, String comboName, Double comboPrice, List<ProductDto> products) {
        this.id = id;
        this.comboName = comboName;
        this.comboPrice = comboPrice;

        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public String getComboName() {
        return comboName;
    }

    public Double getComboPrice() {
        return comboPrice;
    }



    public List<ProductDto> getProducts() {
        return products;
    }
}
