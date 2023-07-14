package com.mindhub.cinema.controllers;

import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import com.mindhub.cinema.utils.enums.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    @Autowired
    ClientServiceInterface clientService;
    @Autowired
    PurchaseServiceInterface purchaseService;

    @PostMapping("/api/current/purchase")
    ResponseEntity<String> create_purchase(Authentication authentication){

        Client clientAuth = clientService.get_full_client(authentication);
        return purchaseService.addPurchaseToClient(clientAuth);


    }
}
