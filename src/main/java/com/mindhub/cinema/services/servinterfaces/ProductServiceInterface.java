package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.ProductDto;
import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.utils.enums.ProductType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface ProductServiceInterface {
    ResponseEntity<String> add_product(Authentication authentication, String productName, Double productPrice, Integer stock, ProductType productType, Integer net_content);

   Set<ProductDto> getAllProducts();

    Product findProductByid(Long productId);

    Boolean existById(Long productId);
}
