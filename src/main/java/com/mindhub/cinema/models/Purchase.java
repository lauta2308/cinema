package com.mindhub.cinema.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Purchase {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Double purchase_price;

    private PurchaseStatus purchaseStatus;

    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @OneToMany(mappedBy="purchase", fetch=FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<>();


    @OneToMany(mappedBy = "purchase", fetch=FetchType.EAGER)
    private Set<PurchaseItem> purchaseItems = new HashSet<>();


    public Purchase() {
    }

    public long getId() {
        return id;
    }

    public Double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(Double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(Set<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }
}
