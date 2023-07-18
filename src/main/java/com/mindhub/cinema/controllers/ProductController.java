package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.ProductDto;
import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.services.servinterfaces.ProductServiceInterface;
import com.mindhub.cinema.utils.enums.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class ProductController {


    @Autowired
    ProductServiceInterface productService;


    @GetMapping("/api/get_all_products")
    Set<ProductDto> get_all_products(){
        return productService.getAllProducts();
    }


    @PostMapping("/api/admin/add_product")
    public ResponseEntity<String> add_product(Authentication authentication, @RequestParam String productName, @RequestParam Double productPrice, @RequestParam Integer stock, @RequestParam ProductType productType, @RequestParam Integer net_content){


        return productService.add_product(authentication, productName, productPrice, stock, productType, net_content);


    }



    @GetMapping("/api/current/get_product_combos")
    public Set<List<ProductDto>> get_product_combos(){
        return productService.getCombos();
    }



}
