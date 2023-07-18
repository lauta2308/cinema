package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.models.Purchase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface PurchaseItemServiceInterface {
    ResponseEntity<String> add_purchase_item(Integer productQuantity, Purchase purchase, Product product);
}
