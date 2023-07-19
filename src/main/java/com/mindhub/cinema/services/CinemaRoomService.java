package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.CreateRoomDto;
import com.mindhub.cinema.models.CinemaRoom;
import com.mindhub.cinema.models.Seat;
import com.mindhub.cinema.repositories.CinemaRoomRepository;
import com.mindhub.cinema.services.servinterfaces.CinemaRoomServiceInterface;
import com.mindhub.cinema.services.servinterfaces.SeatServiceInterface;
import com.mindhub.cinema.utils.enums.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CinemaRoomService implements CinemaRoomServiceInterface {

    @Autowired
    CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    SeatServiceInterface seatService;

    @Override
    @Transactional
    public ResponseEntity<String> create_cinema_room(CreateRoomDto createRoomDto) {


            seatService.addSeats(createRoomDto.getCapacity(),  cinemaRoomRepository.save(new CinemaRoom(createRoomDto.getRoomName(), createRoomDto.getCapacity(), createRoomDto.getRoomType())));


                return new ResponseEntity<>("Room made, seats added", HttpStatus.CREATED);




    }

    public boolean roomNameDuplicated(String roomName){
        return cinemaRoomRepository.existsByRoomName(roomName);
    }



}
