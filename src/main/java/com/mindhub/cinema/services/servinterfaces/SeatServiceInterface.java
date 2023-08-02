package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.models_dtos.SeatDto;
import com.mindhub.cinema.models.CinemaRoom;
import com.mindhub.cinema.models.Seat;
import com.mindhub.cinema.models.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface SeatServiceInterface {
    ResponseEntity<String> addSeats(Integer capacity, CinemaRoom saveRoom);

    Set<Seat> getRoomSeats(CinemaRoom cinemaRoom);

    Set<SeatDto> availableSeats(Set<Seat> show_room_seats, Set<Ticket> showTicketsSold);


    boolean existsByIdAndSeatPlaceAndCinemaRoom(Long seatId, Integer seatPlace, CinemaRoom cinemaRoom);

    List<SeatDto> getSeatsByCinemaRoom(Long cinemaRoomId);
}
