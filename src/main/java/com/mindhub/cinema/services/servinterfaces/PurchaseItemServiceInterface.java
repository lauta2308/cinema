package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.AddPurchaseItemDto;
import com.mindhub.cinema.models.Purchase;

import java.util.Set;

public interface PurchaseItemServiceInterface {


    void save_purchase_items(Set<AddPurchaseItemDto> purchaseItems, Purchase purchase);
}
