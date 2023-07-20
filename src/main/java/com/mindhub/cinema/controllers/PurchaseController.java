package com.mindhub.cinema.controllers;

import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    @Autowired
    ClientServiceInterface clientService;
    @Autowired
    PurchaseServiceInterface purchaseService;

    @PostMapping("/api/current/purchase")
    ResponseEntity<String> create_purchase(Authentication authentication){

      return new ResponseEntity<>(purchaseService.addPurchaseToClient(clientService.get_full_client(authentication)), HttpStatus.CREATED);




    }
}
