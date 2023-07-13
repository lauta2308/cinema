package com.mindhub.cinema.services;

import com.mindhub.cinema.models.CinemaRoom;
import com.mindhub.cinema.models.Seat;
import com.mindhub.cinema.repositories.SeatRepository;
import com.mindhub.cinema.services.servinterfaces.SeatServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SeatService implements SeatServiceInterface {

    @Autowired
    SeatRepository seatRepository;

    @Override
    @Transactional
    public ResponseEntity<String> addSeats(Integer capacity, CinemaRoom saveRoom) {
        int f = 1;
        for (int i = f; i <= saveRoom.getCapacity() ; i++) {
            seatRepository.save(new Seat(i, saveRoom));
            f++;

        }

        // Si se modifica el mensaje de respuesta, modificar también en el cinema room service (clase)

        if(f == capacity){
            return new ResponseEntity<>("Seats added", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Could not add seats, try again", HttpStatus.BAD_REQUEST);
        }

    }
}
