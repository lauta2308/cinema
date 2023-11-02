package com.mindhub.cinema.controllers;

import com.mindhub.cinema.dtos.models_dtos.PurchaseDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import com.mindhub.cinema.services.servinterfaces.TicketServiceInterface;
import com.mindhub.cinema.utils.apiUtils.PurchaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {

    @Autowired
    ClientServiceInterface clientService;
    @Autowired
    PurchaseServiceInterface purchaseService;

    @Autowired
    TicketServiceInterface ticketService;



    @PostMapping("/api/current/purchase")
    ResponseEntity<String> create_purchase(Authentication authentication){

      return new ResponseEntity<>(purchaseService.addPurchaseToClient(clientService.get_full_client(authentication)), HttpStatus.CREATED);

    }


    @GetMapping("/api/current/purchase/{purchaseId}")
    ResponseEntity<Object> get_purchase(Authentication authentication, @PathVariable Long purchaseId){

        Purchase purchase;
        if(purchaseService.existsById(purchaseId)){
            purchase = purchaseService.findPurchaseById(purchaseId);
        } else {
            return new ResponseEntity<>("Purchase not found", HttpStatus.BAD_REQUEST);
        }

       if(purchase.getClient().getId() != clientService.get_authenticated_user(authentication).getId()){
           return new ResponseEntity<>("Purchase does not belong to client", HttpStatus.BAD_REQUEST);
       }


       return new ResponseEntity<>(PurchaseUtils.purchaseToDto(purchase), HttpStatus.ACCEPTED);
    }


    @PatchMapping("/api/current/purchase/{purchaseId}")
    ResponseEntity<Object> complete_purchase(Authentication authentication, @PathVariable Long purchaseId){

        Purchase purchase;
        if(purchaseService.existsById(purchaseId)){
            purchase = purchaseService.findPurchaseById(purchaseId);
        } else {
            return new ResponseEntity<>("Purchase not found", HttpStatus.BAD_REQUEST);
        }

        if(purchase.getClient().getId() != clientService.get_authenticated_user(authentication).getId()){
            return new ResponseEntity<>("Purchase dont belong to client", HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(purchaseService.complete_purchase(purchase), HttpStatus.ACCEPTED);


    }


    @GetMapping("/api/current/get_completed_purchases")
    public List<PurchaseDto> completedPurchases(Authentication authentication){

        Client client = clientService.get_full_client(authentication);

        return PurchaseUtils.filterAndSortCompletedPurchases(client.getPurchases());


    }


    @GetMapping("/api/current/get_used_purchases")
    public List<PurchaseDto> usedPurchases(Authentication authentication){

        Client client = clientService.get_full_client(authentication);

        return PurchaseUtils.filterAndSortUsedPurchases(client.getPurchases());


    }


}
