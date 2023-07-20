package com.mindhub.cinema.controllers;

import com.mindhub.cinema.dtos.AddPurchaseItemDto;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.services.servinterfaces.ProductServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseItemServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import com.mindhub.cinema.utils.apiUtils.PurchaseItemUtils;
import com.mindhub.cinema.utils.enums.PurchaseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class PurchaseItemController {

    @Autowired
    PurchaseItemServiceInterface purchaseItemService;

    @Autowired
    PurchaseServiceInterface purchaseService;

    @Autowired
    ProductServiceInterface productService;

    @PostMapping("/api/current/purchase/add_purchase_item")
    public ResponseEntity<String> add_purchase_item(Authentication authentication, @RequestBody Set<AddPurchaseItemDto> purchaseItems){


        if (purchaseItems == null || purchaseItems.isEmpty()) {
            return new ResponseEntity<>("Set is empty", HttpStatus.BAD_REQUEST);
        }


        if(!PurchaseItemUtils.allPurchaseIdsAreEqual(purchaseItems)){
            return new ResponseEntity<>("All items should contain the same purchase Id", HttpStatus.BAD_REQUEST);
        }


        AddPurchaseItemDto firstPurchaseItem = PurchaseItemUtils.getFirstPurchaseItem(purchaseItems);

        Purchase purchase;

        if(!purchaseService.existsById(firstPurchaseItem.getPurchaseId())){
            return new ResponseEntity<String>("Purchase not found", HttpStatus.CONFLICT);
        } else {
            purchase = purchaseService.findPurchaseById(firstPurchaseItem.getPurchaseId());
        }

        if(purchase.getPurchaseStatus() != PurchaseStatus.IN_PROGRESS){
            return new ResponseEntity<String>("Purchase not valid", HttpStatus.CONFLICT);
        }


        String validateProductsAndStock = productService.allProductsExist(purchaseItems);

        if(!"All the products are valid, stock available".equals(validateProductsAndStock)){
            return new ResponseEntity<>(validateProductsAndStock, HttpStatus.BAD_REQUEST);
        }


        if (purchase.getClient().getEmail() != authentication.getName()) {

            return new ResponseEntity<String>("Purchase and user not match", HttpStatus.CONFLICT);

        }



        purchaseItemService.save_purchase_items(purchaseItems, purchase);

        return new ResponseEntity<>("Items added to purchase", HttpStatus.CREATED);


    }




}
