package com.mindhub.cinema.services;

import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.models.PurchaseItem;
import com.mindhub.cinema.repositories.ProductRepository;
import com.mindhub.cinema.repositories.PurchaseItemRepository;
import com.mindhub.cinema.services.servinterfaces.ProductServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseItemServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PurchaseItemService implements PurchaseItemServiceInterface {

    @Autowired
    PurchaseServiceInterface purchaseService;

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    PurchaseItemRepository purchaseItemRepository;

    @Autowired
    ProductRepository productRepository;


    @Override
    public ResponseEntity<String> add_purchase_item(Integer productQuantity, Purchase purchase, Product product) {


        PurchaseItem purchaseItem = purchaseItemRepository.save(new PurchaseItem(productQuantity, purchase, product));

        purchaseItem.addPriceToPurchase();
        purchaseItem.decreaseProductStock();
        productRepository.save(product);


        return new ResponseEntity<>("Item added to purchase", HttpStatus.CREATED);

    }


}