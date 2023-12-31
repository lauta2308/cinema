package com.mindhub.cinema.models;

import com.mindhub.cinema.utils.apiUtils.DateUtils;
import com.mindhub.cinema.utils.enums.PaymentMethod;
import com.mindhub.cinema.utils.enums.PurchaseStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Entity
public class Purchase {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Double purchase_price = 0.0;

    private PurchaseStatus purchaseStatus = PurchaseStatus.PENDING_TO_PAY;

    private PaymentMethod paymentMethod = PaymentMethod.NOT_SELECTED;

    private LocalDateTime createdAt = LocalDateTime.now();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @OneToMany(mappedBy="purchase", fetch=FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<>();


    @OneToMany(mappedBy = "purchase", fetch=FetchType.EAGER)
    private List<PurchaseItem> purchaseItems = new ArrayList<>();

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<ProductCombo> productCombos = new ArrayList<>();


    public Purchase() {
    }

    public Purchase(Client client) {
        this.client = client;
        client.getPurchases().add(this);
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public List<ProductCombo> getProductCombos() {
        return productCombos;
    }

    public void setProductCombos(List<ProductCombo> productCombos) {
        this.productCombos = productCombos;
    }


}
