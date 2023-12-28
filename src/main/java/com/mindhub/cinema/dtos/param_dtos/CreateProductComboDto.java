package com.mindhub.cinema.dtos.param_dtos;

import com.mindhub.cinema.dtos.models_dtos.ProductDto;

import java.util.List;

public class CreateProductComboDto {

    private String comboName;

    private Double comboPrice;

    private boolean templateCombo = true;

    private List<ProductDto> products;

    public CreateProductComboDto() {
    }

    public CreateProductComboDto(String comboName, Double comboPrice, List<ProductDto> products) {
        this.comboName = comboName;
        this.comboPrice = comboPrice;
        this.products = products;
    }

    public String getComboName() {
        return comboName;
    }

    public double getComboPrice() {
        return comboPrice;
    }

    public boolean isTemplateCombo() {
        return templateCombo;
    }

    public List<ProductDto> getProducts() {
        return products;
    }
}
