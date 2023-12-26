package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.models_dtos.CinemaRoomDto;
import com.mindhub.cinema.models.CinemaRoom;
import java.util.List;
import java.util.stream.Collectors;

public class CinemaRoomUtils {

    public static List <CinemaRoomDto> cinemaRoomsToDto(List<CinemaRoom> cinemaRooms) {
        return cinemaRooms.stream().map(cinemaRoom -> new CinemaRoomDto(cinemaRoom)).collect(Collectors.toList());
    }




}
