package com.mindhub.cinema.services;

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
    public ResponseEntity<String> create_cinema_room(String roomName, Integer capacity, RoomType roomType) {


            seatService.addSeats(capacity,  cinemaRoomRepository.save(new CinemaRoom(roomName, capacity,roomType)));


                return new ResponseEntity<>("Room made, seats added", HttpStatus.CREATED);




    }

    public boolean roomNameDuplicated(String roomName){
        return cinemaRoomRepository.existsByRoomName(roomName);
    }



}
