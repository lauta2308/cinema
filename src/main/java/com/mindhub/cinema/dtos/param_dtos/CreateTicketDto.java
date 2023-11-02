package com.mindhub.cinema.dtos.param_dtos;

import com.mindhub.cinema.utils.enums.CustomerAge;

public class CreateTicketDto {

    private Long ticketId;

   private Long showId;

   // seat id
   private Long id;

   private Integer seatPlace;

   private CustomerAge customerAge = CustomerAge.ADULT;

   private boolean available = true;

    public CreateTicketDto() {
    }

    public CreateTicketDto(Long showId, Long id, Integer seatPlace) {
        this.showId = showId;
        this.id = id;
        this.seatPlace = seatPlace;
    }

    public CreateTicketDto(Long showId, Long id, Integer seatPlace, CustomerAge customerAge, Boolean available) {
        this.showId = showId;
        this.id = id;
        this.seatPlace = seatPlace;
        this.customerAge = customerAge;
        this.available = available;
    }

    public CreateTicketDto(Long ticketId, Long showId, Long id, Integer seatPlace, CustomerAge customerAge, boolean available) {
        this.ticketId = ticketId;
        this.showId = showId;
        this.id = id;
        this.seatPlace = seatPlace;
        this.customerAge = customerAge;
        this.available = available;
    }

    public Long getTicketId() {
        return ticketId;
    }



    public Long getShowId() {
        return showId;
    }

    public Long getId() {
        return id;
    }

    public Integer getSeatPlace() {
        return seatPlace;
    }

    public CustomerAge getCustomerAge() {
        return customerAge;
    }

    public boolean available() {
        return available;
    }
}
