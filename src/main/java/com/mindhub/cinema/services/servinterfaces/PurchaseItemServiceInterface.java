package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.models.Purchase;

public interface PurchaseItemServiceInterface {
    void add_purchase_item(Integer productQuantity, Purchase purchase, Product product);
}
