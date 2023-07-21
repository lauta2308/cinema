package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.param_dtos.AddPurchaseItemDto;
import com.mindhub.cinema.dtos.param_dtos.CreateProductDto;
import com.mindhub.cinema.dtos.models_dtos.ProductDto;

import java.util.Set;

public interface ProductServiceInterface {
    void add_product(CreateProductDto createProductDto);

   Set<ProductDto> getAllProducts();


    boolean existsByName(String productName);

    String allProductsExist(Set<AddPurchaseItemDto> purchaseItems);
}
