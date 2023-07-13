package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.utils.enums.RoomType;
import org.springframework.http.ResponseEntity;

public interface CinemaRoomServiceInterface {
    ResponseEntity<String> create_cinema_room(String roomName, Integer capacity, RoomType roomType);
}
