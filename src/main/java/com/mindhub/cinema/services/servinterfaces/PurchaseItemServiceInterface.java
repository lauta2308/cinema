package com.mindhub.cinema.services.servinterfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface PurchaseItemServiceInterface {
    ResponseEntity<String> add_purchase_item(Authentication authentication, Integer productQuantity, Long purchaseId, Long productId);
}
