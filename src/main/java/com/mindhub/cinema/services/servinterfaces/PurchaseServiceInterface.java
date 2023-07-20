package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.CreateTicketDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface PurchaseServiceInterface {
    String addPurchaseToClient(Client client);

    Purchase findPurchaseById(Long id);

    Boolean existsById(Long purchaseId);

}
