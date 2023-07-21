package com.mindhub.cinema.services;

import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.repositories.PurchaseRepository;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService implements PurchaseServiceInterface {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public String addPurchaseToClient(Client client) {
        Purchase purchase = purchaseRepository.save(new Purchase(client));

        return String.valueOf(purchase.getId());



    }

    @Override
    public Purchase findPurchaseById(Long id) {
        return purchaseRepository.findById(id).get();
    }


    @Override
    public Boolean existsById(Long purchaseId) {

        return purchaseRepository.existsById(purchaseId);
    }

    @Override
    public void save(Client client) {
        purchaseRepository.save(new Purchase(client));
    }


}
