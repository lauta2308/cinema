package com.mindhub.cinema.dtos;

import com.mindhub.cinema.utils.enums.RoomType;

public class CreateRoomDto {

    private String roomName;

    private Integer capacity;

    private RoomType roomType;

    public CreateRoomDto(String roomName, Integer capacity, RoomType roomType) {
        this.roomName = roomName;
        this.capacity = capacity;
        this.roomType = roomType;
    }


    public String getRoomName() {
        return roomName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public RoomType getRoomType() {
        return roomType;
    }
}
