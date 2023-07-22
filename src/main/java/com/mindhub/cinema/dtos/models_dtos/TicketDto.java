package com.mindhub.cinema.dtos.models_dtos;

import com.mindhub.cinema.dtos.models_dtos.ShowDto;
import com.mindhub.cinema.models.Ticket;
import com.mindhub.cinema.utils.enums.CustomerAge;
import com.mindhub.cinema.utils.enums.TicketPromo;
import com.mindhub.cinema.utils.enums.TicketStatus;

public class TicketDto {

    private long id;

    private Long seatId;

    private Integer seatPlace;

    private TicketPromo ticketPromo;

    private CustomerAge customerAge;

    private Double ticketPrice;


    private TicketStatus ticketStatus;



    private ShowDto show;

    public TicketDto(Ticket ticket) {
        this.id = ticket.getId();
        this.seatId = ticket.getSeatId();
        this.seatPlace = ticket.getSeatPlace();
        this.ticketPromo = ticket.getTicketPromo();
        this.customerAge = ticket.getCustomerAge();
        this.ticketPrice = ticket.getTicketPrice();

        this.show = new ShowDto(ticket.getShow());
        this.ticketStatus = ticket.getTicketStatus();
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


    public ShowDto getShow() {
        return show;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }
}
