package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;

public interface PurchaseServiceInterface {
    String addPurchaseToClient(Client client);

    Purchase findPurchaseById(Long id);

    Boolean existsById(Long purchaseId);

    void save(Client client);

    void replacePurchasePrice(Purchase purchase, Double totalPrice);
}
