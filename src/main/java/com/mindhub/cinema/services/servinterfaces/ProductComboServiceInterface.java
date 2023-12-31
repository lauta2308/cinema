package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.models_dtos.ProductComboDto;
import com.mindhub.cinema.dtos.param_dtos.CreateProductComboDto;
import com.mindhub.cinema.dtos.param_dtos.EditProductComboDto;
import com.mindhub.cinema.models.Purchase;

import java.util.List;

public interface ProductComboServiceInterface {
    List<ProductComboDto> getTemplateCombos();


    String addProductCombosToPurchase(List<ProductComboDto> productComboDtoList, Purchase purchase);

    void createProductCombo(CreateProductComboDto createProductComboDto);

    void editProductCombo(EditProductComboDto editProductComboDto);
}
