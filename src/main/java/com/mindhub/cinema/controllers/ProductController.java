package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.ProductDto;
import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.services.servinterfaces.ProductServiceInterface;
import com.mindhub.cinema.utils.enums.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

        if(authentication == null){
            return new ResponseEntity<>("Login first", HttpStatus.FORBIDDEN);
        }


        if(productName.isBlank() && productPrice.toString().isBlank() && stock.toString().isBlank() && productType.toString().isBlank() && net_content.toString().isBlank()){
            return new ResponseEntity<>("Empty fields", HttpStatus.BAD_REQUEST);
        }

        if(productPrice.toString().isBlank()){
            return new ResponseEntity<>("Product price can not be empty", HttpStatus.BAD_REQUEST);
        }


        if(stock.toString().isBlank()){
            return new ResponseEntity<>("Product stock can not be empty", HttpStatus.BAD_REQUEST);
        }

        if(productType.toString().isBlank()){
            return new ResponseEntity<>("Product type can not be empty", HttpStatus.BAD_REQUEST);
        }

        if(net_content.toString().isBlank()){
            return new ResponseEntity<>("Net content cannot be blank", HttpStatus.BAD_REQUEST);
        }



        if(!authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ADMIN"))){
            return new ResponseEntity<>("Not an admin", HttpStatus.CONFLICT);
        }
        


        return productService.add_product(productName, productPrice, stock, productType, net_content);


    }



    @GetMapping("/api/current/get_product_combos")
    public Set<List<ProductDto>> get_product_combos(){
        return productService.getCombos();
    }



}
