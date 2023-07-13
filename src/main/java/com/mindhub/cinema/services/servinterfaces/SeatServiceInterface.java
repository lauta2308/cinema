package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.models.CinemaRoom;
import org.springframework.http.ResponseEntity;

public interface SeatServiceInterface {
    ResponseEntity<String> addSeats(Integer capacity, CinemaRoom saveRoom);
}
