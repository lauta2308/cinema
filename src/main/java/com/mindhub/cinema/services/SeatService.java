package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.SeatDto;
import com.mindhub.cinema.models.*;
import com.mindhub.cinema.repositories.PurchaseRepository;
import com.mindhub.cinema.repositories.SeatRepository;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import com.mindhub.cinema.services.servinterfaces.SeatServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public Set<Seat> getRoomSeats(CinemaRoom cinemaRoom) {
        return seatRepository.findAll().stream().filter(seat -> seat.getCinemaRoom() == cinemaRoom).collect(Collectors.toSet());
    }

    @Override
    public Set<SeatDto> availableSeats(Set<Seat> show_room_seats, Set<Ticket> showTicketsSold) {
        Set<Seat> unassignedSeats = new HashSet<>(show_room_seats);

        for (Ticket ticket : showTicketsSold) {
            Long seatId = ticket.getSeatId();

            // Filtro los asientos de la sala y solo dejo aquellos que no estén asignados a ningun ticket
            unassignedSeats.removeIf(seat -> seat.getId() == seatId || !seat.getAvailable());
        }

        return unassignedSeats.stream().map(seat -> new SeatDto(seat)).collect(Collectors.toSet());

    }

    @Override
    public boolean checkValidSeatIdPlaceRoom(Long seatId, Integer seatPlace, CinemaRoom cinemaRoom) {
        return seatRepository.existsByIdAndSeatPlaceAndCinemaRoom(seatId, seatPlace, cinemaRoom);
    }


}
