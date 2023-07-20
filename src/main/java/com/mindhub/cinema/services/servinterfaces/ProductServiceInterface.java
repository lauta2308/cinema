package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.AddPurchaseItemDto;
import com.mindhub.cinema.dtos.CreateProductDto;
import com.mindhub.cinema.dtos.ProductDto;
import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.utils.enums.ProductType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;

public interface ProductServiceInterface {
    void add_product(CreateProductDto createProductDto);

   Set<ProductDto> getAllProducts();


    Set<List<ProductDto>> getCombos();

    boolean existsByName(String productName);

    String allProductsExist(Set<AddPurchaseItemDto> purchaseItems);
}
