package com.mindhub.cinema.dtos.param_dtos;

import com.mindhub.cinema.utils.enums.CustomerAge;

public class CreateTicketDto {


   private Long showId;

   private Long id;

   private Integer seatPlace;

   private CustomerAge customerAge;


    public CreateTicketDto(Long showId, Long id, Integer seatPlace, CustomerAge customerAge) {
        this.showId = showId;
        this.id = id;
        this.seatPlace = seatPlace;
        this.customerAge = customerAge;
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
}
