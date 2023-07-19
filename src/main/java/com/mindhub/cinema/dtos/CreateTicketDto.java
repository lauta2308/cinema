package com.mindhub.cinema.dtos;

public class CreateTicketDto {

   private Long purchaseId;

   private Long showId;

   private Long seatId;

   private Integer seatPlace;


    public CreateTicketDto(Long purchaseId, Long showId, Long seatId, Integer seatPlace) {
        this.purchaseId = purchaseId;
        this.showId = showId;
        this.seatId = seatId;
        this.seatPlace = seatPlace;
    }

    public Long getPurchaseId() {
        return purchaseId;
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
}
