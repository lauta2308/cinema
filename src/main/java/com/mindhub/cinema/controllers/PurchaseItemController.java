package com.mindhub.cinema.controllers;

import com.mindhub.cinema.services.servinterfaces.PurchaseItemServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseItemController {

    @Autowired
    PurchaseItemServiceInterface purchaseItemService;

    @PostMapping("/api/current/purchase/add_purchase_item")
    public ResponseEntity<String> add_purchase_item(Authentication authentication, @RequestParam Integer productQuantity, @RequestParam Long purchaseId, @RequestParam Long productId){


       return purchaseItemService.add_purchase_item(authentication, productQuantity, purchaseId, productId);



    }




}
