package com.mindhub.cinema.dtos.param_dtos;

import com.mindhub.cinema.utils.enums.RoomStatus;
import com.mindhub.cinema.utils.enums.RoomType;

public class EditRoomDto {

    private Long roomId;
    private String roomName;

    private Integer capacity;

    private RoomType roomType;

    private RoomStatus roomStatus;

    public EditRoomDto(Long roomId, String roomName, Integer capacity, RoomType roomType, RoomStatus roomStatus) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.roomType = roomType;
        this.roomStatus = roomStatus;
    }

    public Long getRoomId() {
        return roomId;
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

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }
}



