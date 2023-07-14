package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;

public interface PurchaseServiceInterface {
    Purchase addPurchaseToClient(Client client);
}
