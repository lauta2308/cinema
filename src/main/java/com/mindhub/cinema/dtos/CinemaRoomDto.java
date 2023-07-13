package com.mindhub.cinema.dtos;

import com.mindhub.cinema.models.CinemaRoom;
import com.mindhub.cinema.utils.enums.RoomStatus;
import com.mindhub.cinema.utils.enums.RoomType;

import java.util.Set;
import java.util.stream.Collectors;

public class CinemaRoomDto {

    private long id;

    private String roomName;

    private Integer capacity;

    private RoomStatus roomStatus;

    private RoomType roomType;

    private Set<SeatDto> seats;


    private Set<ShowDto> shows;


    public CinemaRoomDto(CinemaRoom cinemaRoom) {
        this.id = cinemaRoom.getId();
        this.roomName = cinemaRoom.getRoomName();
        this.capacity = cinemaRoom.getCapacity();
        this.roomStatus = cinemaRoom.getRoomStatus();
        this.roomType = cinemaRoom.getRoomType();
        this.seats = cinemaRoom.getSeats().stream().map(seat -> new SeatDto(seat)).collect(Collectors.toSet());
        this.shows = cinemaRoom.getShows().stream().map(show -> new ShowDto(show)).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Set<SeatDto> getSeats() {
        return seats;
    }

    public Set<ShowDto> getShows() {
        return shows;
    }
}
