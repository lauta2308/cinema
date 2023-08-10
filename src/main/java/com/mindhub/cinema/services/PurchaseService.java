package com.mindhub.cinema.services;

import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.repositories.PurchaseRepository;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import com.mindhub.cinema.services.servinterfaces.TicketServiceInterface;
import com.mindhub.cinema.utils.enums.PurchaseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PurchaseService implements PurchaseServiceInterface {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    TicketServiceInterface ticketService;

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

    @Override
    public void replacePurchasePrice(Purchase purchase, Double totalPrice) {
        purchase.setPurchase_price(totalPrice);
        purchaseRepository.save(purchase);

    }

    @Override
    public void checkAndCancelExpiredPurchases() {

        System.out.println("checking...");
        List<Purchase> pendingPurchases = purchaseRepository.findByPurchaseStatus(PurchaseStatus.PENDING_TO_PAY);

        if(pendingPurchases.size() > 0){
            iteratePendingPurchasesToChangeStatus(pendingPurchases);
        }




    }

    @Override
    public Object complete_purchase(Purchase purchase) {
        purchase.setPurchaseStatus(PurchaseStatus.COMPLETED);
        purchaseRepository.save(purchase);
        return "Purchase completed";
    }

    public void iteratePendingPurchasesToChangeStatus(List<Purchase> pendingPurchases){
        long currentTimeInMillis;
        long purchaseTimeInMillis;
        long timeElapsedInMillis;
        long timeLimitInMillis = TimeUnit.MINUTES.toMillis(15);

        for (Purchase purchase : pendingPurchases) {
            currentTimeInMillis = System.currentTimeMillis();
            purchaseTimeInMillis = LocalDateTime.parse(purchase.getCreatedAt()).atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
            timeElapsedInMillis = currentTimeInMillis - purchaseTimeInMillis;


            System.out.println(timeElapsedInMillis);
            System.out.println(timeLimitInMillis);
            if (timeElapsedInMillis >= timeLimitInMillis) {
                System.out.println("changing purchase status..");

                purchaseStatusToCancelled(purchase);
                ticketService.ticketStatusToCancelled(purchase.getTickets());

            }
        }

    }


    public void purchaseStatusToCancelled(Purchase purchase){
        purchase.setPurchaseStatus(PurchaseStatus.CANCELLED);
        purchaseRepository.save(purchase);
    }


}
