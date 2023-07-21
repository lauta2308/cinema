package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.param_dtos.CreateRoomDto;
import com.mindhub.cinema.models.CinemaRoom;

public interface CinemaRoomServiceInterface {
    void create_cinema_room(CreateRoomDto createRoomDto);


    boolean roomNameDuplicated(String roomName);

    CinemaRoom findByShow_Id(Long showId);
}
