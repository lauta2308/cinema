package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.CreateProductDto;
import com.mindhub.cinema.dtos.ProductDto;
import com.mindhub.cinema.services.servinterfaces.ProductServiceInterface;
import com.mindhub.cinema.utils.apiUtils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<String> add_product(Authentication authentication, @RequestBody CreateProductDto createProductDto){



        if(authentication == null){
            return new ResponseEntity<>("Login first", HttpStatus.FORBIDDEN);
        }

        if(!ValidationUtils.validateAdmin(authentication)){
            return new ResponseEntity<>("Not an admin", HttpStatus.CONFLICT);
        }


        if(createProductDto.getProductName().isBlank() && createProductDto.getProductPrice().toString().isBlank() && createProductDto.getStock().toString().isBlank() && createProductDto.getProductType().toString().isBlank() && createProductDto.getNet_content().toString().isBlank()){
            return new ResponseEntity<>("Empty fields", HttpStatus.BAD_REQUEST);
        }

        if(createProductDto.getProductPrice().toString().isBlank()){
            return new ResponseEntity<>("Product price can not be empty", HttpStatus.BAD_REQUEST);
        }


        if(createProductDto.getStock().toString().isBlank()){
            return new ResponseEntity<>("Product stock can not be empty", HttpStatus.BAD_REQUEST);
        }

        if(createProductDto.getProductType().toString().isBlank()){
            return new ResponseEntity<>("Product type can not be empty", HttpStatus.BAD_REQUEST);
        }

        if(createProductDto.getNet_content().toString().isBlank()){
            return new ResponseEntity<>("Net content cannot be blank", HttpStatus.BAD_REQUEST);
        }




        if(productService.existsByName(createProductDto.getProductName())){
            return new ResponseEntity<>("Product name duplicated", HttpStatus.CONFLICT);
        }


        return productService.add_product(createProductDto);


    }



    @GetMapping("/api/current/get_product_combos")
    public Set<List<ProductDto>> get_product_combos(){
        return productService.getCombos();
    }



}
