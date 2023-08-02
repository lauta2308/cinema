package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.models_dtos.ProductComboDto;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.services.servinterfaces.ProductComboServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class ProductComboController {

    @Autowired
    ProductComboServiceInterface productComboService;

    @Autowired
    PurchaseServiceInterface purchaseService;

    @GetMapping("/api/current/get_product_combos")
    List<ProductComboDto> getProductCombos(){
        return productComboService.getTemplateCombos();
    }



    @Transactional
    @PostMapping("/api/current/add_product_combos")
    ResponseEntity<String> addCombosToPurchase(Authentication authentication, @RequestBody List<ProductComboDto> productComboDtoList, @RequestParam Long purchaseId){




        Purchase purchase = null;

        if( purchaseService.existsById(purchaseId)){
          purchase = purchaseService.findPurchaseById(purchaseId);
        } else {
            return new ResponseEntity<String>("Purchase not found", HttpStatus.CONFLICT);
        }

        if(purchase.getClient().getEmail() != authentication.getName()){
            return new ResponseEntity<>("Purchase do not belong to client", HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(productComboService.addProductCombosToPurchase(productComboDtoList, purchase), HttpStatus.CREATED) ;


    }


}
