package com.mindhub.cinema.dtos;

import com.mindhub.cinema.models.Seat;

public class SeatDto {

    private long id;

    private Integer seatPlace;

    private Boolean available;

    private CinemaRoomDto cinemaRoom;


    public SeatDto(Seat seat) {
        this.id = seat.getId();
        this.seatPlace = seat.getSeatPlace();
        this.available = seat.getAvailable();
        this.cinemaRoom = new CinemaRoomDto(seat.getCinemaRoom());
    }

    public long getId() {
        return id;
    }

    public Integer getSeatPlace() {
        return seatPlace;
    }

    public Boolean getAvailable() {
        return available;
    }

    public CinemaRoomDto getCinemaRoom() {
        return cinemaRoom;
    }
}
