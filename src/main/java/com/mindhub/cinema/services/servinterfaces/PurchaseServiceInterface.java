package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;
import org.springframework.http.ResponseEntity;

public interface PurchaseServiceInterface {
    String addPurchaseToClient(Client client);

    Purchase findPurchaseById(Long id);

    Boolean existById(Long purchaseId);


}
