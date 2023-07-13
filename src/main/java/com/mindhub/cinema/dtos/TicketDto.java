package com.mindhub.cinema.dtos;

import com.mindhub.cinema.models.Ticket;
import com.mindhub.cinema.utils.enums.CustomerAge;
import com.mindhub.cinema.utils.enums.TicketPromo;

public class TicketDto {

    private long id;

    private Long seatId;

    private Integer seatPlace;

    private TicketPromo ticketPromo;

    private CustomerAge customerAge;

    private Double ticketPrice;


    private PurchaseDto purchase;


    private ShowDto show;

    public TicketDto(Ticket ticket) {
        this.id = ticket.getId();
        this.seatId = ticket.getSeatId();
        this.seatPlace = ticket.getSeatPlace();
        this.ticketPromo = ticket.getTicketPromo();
        this.customerAge = ticket.getCustomerAge();
        this.ticketPrice = ticket.getTicketPrice();
        this.purchase = new PurchaseDto(ticket.getPurchase());
        this.show = new ShowDto(ticket.getShow());
    }

    public long getId() {
        return id;
    }

    public Long getSeatId() {
        return seatId;
    }

    public Integer getSeatPlace() {
        return seatPlace;
    }

    public TicketPromo getTicketPromo() {
        return ticketPromo;
    }

    public CustomerAge getCustomerAge() {
        return customerAge;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public PurchaseDto getPurchase() {
        return purchase;
    }

    public ShowDto getShow() {
        return show;
    }
}
