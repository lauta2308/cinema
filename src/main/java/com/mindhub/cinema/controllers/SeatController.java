package com.mindhub.cinema.controllers;

import com.mindhub.cinema.dtos.SeatDto;
import com.mindhub.cinema.models.Seat;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.models.Ticket;
import com.mindhub.cinema.services.servinterfaces.SeatServiceInterface;
import com.mindhub.cinema.services.servinterfaces.ShowServiceInterface;
import com.mindhub.cinema.services.servinterfaces.TicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class SeatController {

    @Autowired
    ShowServiceInterface showService;

    @Autowired
    SeatServiceInterface seatService;

    @Autowired
    TicketServiceInterface ticketService;

    @GetMapping("/api/seats_available")
    public Set<SeatDto> seats_available(@RequestParam Long showId){

        Show show = showService.getShow(showId);

        Set<Seat> show_room_seats = seatService.getRoomSeats(show.getCinemaRoom());


        Set<Ticket> showTicketsSold = ticketService.getTicketsTaken(showId);


        return seatService.availableSeats(show_room_seats, showTicketsSold);


    }

}