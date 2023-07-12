package com.mindhub.cinema.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CinemaRoom {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String roomName;

    private Integer capacity;

    private RoomStatus roomStatus;

    private RoomType roomType;

    @OneToMany(mappedBy="cinemaRoom", fetch=FetchType.EAGER)
    private Set<Seat> seats = new HashSet<>();

    @OneToMany(mappedBy="cinemaRoom", fetch=FetchType.EAGER)
    private Set<Show> shows = new HashSet<>();


    public CinemaRoom() {
    }

    public CinemaRoom(String roomName, Integer capacity, RoomType roomType) {
        this.roomName = roomName;
        this.capacity = capacity;
        this.roomType = roomType;
    }

    public long getId() {
        return id;
    }


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public Set<Show> getShows() {
        return shows;
    }

    public void setShows(Set<Show> shows) {
        this.shows = shows;
    }
}
