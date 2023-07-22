package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.models_dtos.ProductComboDto;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.services.servinterfaces.ProductComboServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import com.mindhub.cinema.utils.exceptions.InsufficientStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@RestController
public class ProductComboController {

    @Autowired
    ProductComboServiceInterface productComboService;

    @Autowired
    PurchaseServiceInterface purchaseService;

    @GetMapping("/api/current/get_product_combos")
    Set<ProductComboDto> getProductCombos(){
        return productComboService.findAll();
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


        try {
            String resultMessage = productComboService.verifyProductsStock(productComboDtoList);
            if (resultMessage.equals("All products verified and stocks decreased.")) {


                return new ResponseEntity<>( productComboService.addProductCombosToPurchase(productComboDtoList, purchase),HttpStatus.CREATED);
            } else {
                return ResponseEntity.badRequest().body(resultMessage);
            }
        } catch (InsufficientStockException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
