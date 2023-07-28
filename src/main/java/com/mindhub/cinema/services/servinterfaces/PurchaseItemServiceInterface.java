package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.param_dtos.AddPurchaseItemDto;
import com.mindhub.cinema.models.Purchase;

import java.util.List;

public interface PurchaseItemServiceInterface {


    void save_purchase_items(List<AddPurchaseItemDto> purchaseItems, Purchase purchase);
}
