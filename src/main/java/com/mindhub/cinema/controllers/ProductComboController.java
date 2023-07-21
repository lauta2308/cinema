package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.models_dtos.ProductComboDto;
import com.mindhub.cinema.services.servinterfaces.ProductComboServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ProductComboController {

    @Autowired
    ProductComboServiceInterface productComboService;

    @GetMapping("/api/current/get_product_comboes")
    Set<ProductComboDto> getProductComboes(){
        return productComboService.findAll();
    }



}
