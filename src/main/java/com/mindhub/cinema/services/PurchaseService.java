package com.mindhub.cinema.services;

import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.repositories.PurchaseRepository;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService implements PurchaseServiceInterface {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public Purchase addPurchaseToClient(Client client) {
        Purchase purchase = purchaseRepository.save(new Purchase(client));
        return purchase;


    }
}
