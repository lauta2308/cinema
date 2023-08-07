package com.mindhub.cinema.models;


import com.mindhub.cinema.utils.enums.CustomerAge;
import com.mindhub.cinema.utils.enums.TicketPromo;
import com.mindhub.cinema.utils.enums.TicketStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Long seatId;

    private Integer seatPlace;

    private TicketPromo ticketPromo = TicketPromo.NORMAL;

    private CustomerAge customerAge = CustomerAge.ADULT;

    private Double ticketPrice;


    private TicketStatus ticketStatus = TicketStatus.TAKEN;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="purchase_id")
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="show_id")
    private Show show;


    public Ticket() {
    }

    public Ticket(Long seatId, Integer seatPlace, CustomerAge customerAge, Show show) {
        this.seatId = seatId;
        this.seatPlace = seatPlace;
        this.customerAge = customerAge;
        this.show = show;
        this.ticketPrice = show.getStandardPrice();
    }

    public Ticket(Long seatId, Integer seatPlace, CustomerAge customerAge, Purchase purchase, Show show) {
        this.seatId = seatId;
        this.seatPlace = seatPlace;
        this.customerAge = customerAge;
        this.purchase = purchase;
        this.show = show;
        this.ticketPrice = show.getStandardPrice();
    }

    public long getId() {
        return id;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Integer getSeatPlace() {
        return seatPlace;
    }

    public void setSeatPlace(Integer seatPlace) {
        this.seatPlace = seatPlace;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public TicketPromo getTicketPromo() {
        return ticketPromo;
    }

    public void setTicketPromo(TicketPromo ticketPromo) {
        this.ticketPromo = ticketPromo;
    }

    public CustomerAge getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(CustomerAge customerAge) {
        this.customerAge = customerAge;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    // Actualizar el precio según edad

    public void updateTicketPriceByAge(){
        if(this.customerAge == CustomerAge.KID || this.customerAge == CustomerAge.RETIRED){
            this.ticketPrice = this.ticketPrice - (this.ticketPrice * 0.05);
        }
    }

    // Sumo el precio del purchase item al precio de la compra
    public void addPriceToPurchase(){
        this.purchase.setPurchase_price(this.purchase.getPurchase_price() + this.getTicketPrice());

    }

}
