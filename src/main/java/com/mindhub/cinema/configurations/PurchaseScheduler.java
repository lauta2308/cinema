package com.mindhub.cinema.configurations;


import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class PurchaseScheduler {


    @Autowired
   PurchaseServiceInterface purchaseService;



    @Scheduled(fixedDelay = 900000)
    public void schedulePurchaseCancellation() {
        purchaseService.checkAndCancelExpiredPurchases();
    }
}
