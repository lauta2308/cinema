package com.mindhub.cinema.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Integer seatPlace;

    private Boolean available = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cinema_room_id")
    private CinemaRoom cinemaRoom;


    public Seat() {
    }

    public Seat(Integer seatPlace, CinemaRoom cinemaRoom) {
        this.seatPlace = seatPlace;
        this.cinemaRoom = cinemaRoom;
    }

    public long getId() {
        return id;
    }

    public Integer getSeatPlace() {
        return seatPlace;
    }

    public void setSeatPlace(Integer seatPlace) {
        this.seatPlace = seatPlace;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }
}
