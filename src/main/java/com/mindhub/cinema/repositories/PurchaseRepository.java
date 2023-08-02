package com.mindhub.cinema.repositories;

import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.utils.enums.PurchaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByPurchaseStatus(PurchaseStatus pendingToPay);
}
