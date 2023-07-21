package com.mindhub.cinema.dtos.param_dtos;

import com.mindhub.cinema.utils.enums.CustomerAge;

public class CreateTicketDto {


   private Long showId;

   private Long seatId;

   private Integer seatPlace;

   private CustomerAge customerAge;


    public CreateTicketDto(Long purchaseId, Long showId, Long seatId, Integer seatPlace, CustomerAge customerAge) {
        this.showId = showId;
        this.seatId = seatId;
        this.seatPlace = seatPlace;
        this.customerAge = customerAge;
    }



    public Long getShowId() {
        return showId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public Integer getSeatPlace() {
        return seatPlace;
    }

    public CustomerAge getCustomerAge() {
        return customerAge;
    }
}
