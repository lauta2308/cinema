package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.models_dtos.CinemaRoomDto;
import com.mindhub.cinema.dtos.param_dtos.CreateRoomDto;
import com.mindhub.cinema.dtos.param_dtos.EditRoomDto;
import com.mindhub.cinema.models.CinemaRoom;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CinemaRoomServiceInterface {
    void create_cinema_room(CreateRoomDto createRoomDto);


    boolean roomNameDuplicated(String roomName);

    CinemaRoom findByShow_Id(Long showId);

    List<CinemaRoomDto> getRooms();

    boolean existById(Long roomId);

    void editRoom(EditRoomDto editRoomDto);
}
