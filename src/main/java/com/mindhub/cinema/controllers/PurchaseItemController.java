package com.mindhub.cinema.controllers;

import com.mindhub.cinema.dtos.AddPurchaseItemDto;
import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.services.servinterfaces.ProductServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseItemServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import com.mindhub.cinema.utils.enums.PurchaseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseItemController {

    @Autowired
    PurchaseItemServiceInterface purchaseItemService;

    @Autowired
    PurchaseServiceInterface purchaseService;

    @Autowired
    ProductServiceInterface productService;

    @PostMapping("/api/current/purchase/add_purchase_item")
    public ResponseEntity<String> add_purchase_item(Authentication authentication, @RequestBody AddPurchaseItemDto addPurchaseItemDto){


        Purchase purchase;

        if(!purchaseService.existById(addPurchaseItemDto.getPurchaseId())){
            return new ResponseEntity<String>("Purchase not found", HttpStatus.CONFLICT);
        } else {
            purchase = purchaseService.findPurchaseById(addPurchaseItemDto.getPurchaseId());
        }

        if(purchase.getPurchaseStatus() != PurchaseStatus.IN_PROGRESS){
            return new ResponseEntity<String>("Purchase not valid", HttpStatus.CONFLICT);
        }




        Product product;
        if(!productService.existById(addPurchaseItemDto.getProductId())){
            return new ResponseEntity<String>("Product not found", HttpStatus.CONFLICT);
        } else {
            product = productService.findProductByid(addPurchaseItemDto.getProductId());
        }


        if (purchase.getClient().getEmail() != authentication.getName()) {

            return new ResponseEntity<String>("Purchase and user not match", HttpStatus.CONFLICT);

        }


        if (product.getStock() < addPurchaseItemDto.getProductQuantity()) {
            return new ResponseEntity<String>("Less stock than quantity", HttpStatus.CONFLICT);

        }

        purchaseItemService.add_purchase_item(addPurchaseItemDto.getProductQuantity(), purchase, product);

        return new ResponseEntity<>("Item added to purchase", HttpStatus.CREATED);


    }




}
