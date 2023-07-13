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


        if(roomName.isBlank() || capacity <= 0 || roomType.toString().isBlank()){

            return new ResponseEntity<>("fields not complete", HttpStatus.BAD_REQUEST);
        }


        // Chequeo que no haya otra sala con el mismo nombre

        CinemaRoom checkRoomName = cinemaRoomRepository.findByroomName(roomName);

        if(checkRoomName != null){
            return new ResponseEntity<>("There is another room with the same name, change", HttpStatus.CONFLICT);
        }

        else {
            CinemaRoom saveRoom = cinemaRoomRepository.save(new CinemaRoom(roomName, capacity,roomType));

            // Create seats and add to room


            ResponseEntity<String> addSeats =  seatService.addSeats(capacity, saveRoom);

            // respuestas



                return new ResponseEntity<>("Room made, seats added", HttpStatus.CREATED);



        }


    }
}
