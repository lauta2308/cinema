package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.CreateRoomDto;
import com.mindhub.cinema.utils.enums.RoomType;
import org.springframework.http.ResponseEntity;

public interface CinemaRoomServiceInterface {
    ResponseEntity<String> create_cinema_room(CreateRoomDto createRoomDto);


    boolean roomNameDuplicated(String roomName);
}
