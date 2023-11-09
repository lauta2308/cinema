package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.param_dtos.AddPurchaseItemDto;
import com.mindhub.cinema.dtos.param_dtos.CreateProductDto;
import com.mindhub.cinema.dtos.models_dtos.ProductDto;
import com.mindhub.cinema.models.Product;

import java.util.List;
import java.util.Set;

public interface ProductServiceInterface {
    void add_product(CreateProductDto createProductDto);

   Set<ProductDto> getAllProducts();


    boolean existsByName(String productName);

    String allProductsExist(List<AddPurchaseItemDto> purchaseItems);

    void save(Product product);


    boolean existsById(Long productId);

    void changeAvailability(Long productId);
}
