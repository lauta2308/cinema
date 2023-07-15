package com.mindhub.cinema.services;

import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.models.PurchaseItem;
import com.mindhub.cinema.repositories.ClientRepository;
import com.mindhub.cinema.repositories.ProductRepository;
import com.mindhub.cinema.repositories.PurchaseItemRepository;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import com.mindhub.cinema.services.servinterfaces.ProductServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseItemServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import com.mindhub.cinema.utils.enums.PurchaseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<String> add_purchase_item(Authentication authentication, Integer productQuantity, Long purchaseId, Long productId) {




        Boolean purchaseExists = purchaseService.existById(purchaseId);

        Purchase purchase;

        if(!purchaseExists){
            return new ResponseEntity<String>("Purchase not found", HttpStatus.CONFLICT);
        } else {
            purchase = purchaseService.findPurchaseById(purchaseId);
        }

        if(purchase.getPurchaseStatus() != PurchaseStatus.IN_PROGRESS){
            return new ResponseEntity<String>("Purchase not valid", HttpStatus.CONFLICT);
        }

        Boolean productExists = productService.existById(productId);


        Product product;
        if(!productExists){
            return new ResponseEntity<String>("Product not found", HttpStatus.CONFLICT);
        } else {
            product = productService.findProductByid(productId);
        }


         if (purchase.getClient().getEmail() != authentication.getName()) {

            return new ResponseEntity<String>("Purchase and user not match", HttpStatus.CONFLICT);

        } else if (product.getStock() < productQuantity) {
            return new ResponseEntity<String>("Less stock than quantity", HttpStatus.CONFLICT);

        } else {
            PurchaseItem purchaseItem = purchaseItemRepository.save(new PurchaseItem(productQuantity, purchase, product));

            purchaseItem.addPriceToPurchase();
            purchaseItem.decreaseProductStock();
            productRepository.save(product);


            return new ResponseEntity<String>("Item added to purchase", HttpStatus.CREATED);

        }

    }
}
