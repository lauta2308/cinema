package com.mindhub.cinema.dtos;

import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.utils.enums.PaymentMethod;
import com.mindhub.cinema.utils.enums.PurchaseStatus;

import java.util.Set;
import java.util.stream.Collectors;

public class PurchaseDto {

    private long id;

    private Double purchase_price;

    private PurchaseStatus purchaseStatus;

    private PaymentMethod paymentMethod;



    private Set<TicketDto> tickets;

    private Set<PurchaseItemDto> purchaseItems;


    public PurchaseDto(Purchase purchase) {
        this.id = purchase.getId();
        this.purchase_price = purchase.getPurchase_price();
        this.purchaseStatus = purchase.getPurchaseStatus();
        this.paymentMethod = purchase.getPaymentMethod();
        this.tickets = purchase.getTickets().stream().map(ticket -> new TicketDto(ticket)).collect(Collectors.toSet());
        this.purchaseItems = purchase.getPurchaseItems().stream().map(purchaseItem -> new PurchaseItemDto(purchaseItem)).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public Double getPurchase_price() {
        return purchase_price;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Set<TicketDto> getTickets() {
        return tickets;
    }

    public Set<PurchaseItemDto> getPurchaseItems() {
        return purchaseItems;
    }
}
