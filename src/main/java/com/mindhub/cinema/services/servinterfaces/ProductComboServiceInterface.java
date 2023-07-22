package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.models_dtos.ProductComboDto;
import com.mindhub.cinema.dtos.param_dtos.BuyProductComboDto;
import com.mindhub.cinema.models.Purchase;

import java.util.List;
import java.util.Set;

public interface ProductComboServiceInterface {
    Set<ProductComboDto> findAll();

    String verifyProductsStock(List<BuyProductComboDto> productComboDtoList);

    String addProductCombosToPurchase(List<BuyProductComboDto> productComboDtoList, Purchase purchase);
}
