package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.models_dtos.ProductComboDto;
import com.mindhub.cinema.models.Purchase;

import java.util.List;
import java.util.Set;

public interface ProductComboServiceInterface {
    Set<ProductComboDto> findAll();

    String verifyProductsStock(List<ProductComboDto> productComboDtoList);

    String addProductCombosToPurchase(List<ProductComboDto> productComboDtoList, Purchase purchase);
}
