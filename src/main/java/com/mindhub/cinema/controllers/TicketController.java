package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.param_dtos.ChangeTicketsDto;
import com.mindhub.cinema.dtos.param_dtos.CreateTicketDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.services.servinterfaces.*;
import com.mindhub.cinema.utils.apiUtils.TicketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class TicketController {

    @Autowired
    ClientServiceInterface clientService;

    @Autowired
    PurchaseServiceInterface purchaseService;

    @Autowired
    ShowServiceInterface showService;

    @Autowired
    TicketServiceInterface ticketService;

    @Autowired
    SeatServiceInterface seatService;


    // Crea los tickets para mostrar el precio pero no los guarda.
    @PostMapping("/api/current/ticket_request")
    public ResponseEntity<Object> ticket_request(Authentication authentication, @RequestBody Set<CreateTicketDto> createTicketDtoSet){

        CreateTicketDto firstTicket = TicketUtils.getFirstTicket(createTicketDtoSet);



        // Verifico que el show existe

        if(!showService.existsById(firstTicket.getShowId())) {

            return new ResponseEntity<>("Show not found", HttpStatus.CONFLICT);
        }

        // Busco el show seleccionado

        Show showSelected = showService.getShow(firstTicket.getShowId());

        return new ResponseEntity<>(TicketUtils.ticketRequest(createTicketDtoSet, showSelected), HttpStatus.OK);

    }


    // Create ticket
    @PostMapping("/api/current/create_ticket")
    public ResponseEntity<String> create_ticket(Authentication authentication, @RequestBody Set<CreateTicketDto> createTicketDtoSet){





        if (!TicketUtils.areAllSeatPlacesUnique(createTicketDtoSet)) {

            return new ResponseEntity<>("Seat places of all tickets should be unique", HttpStatus.BAD_REQUEST);

        }

        if (!TicketUtils.areAllshowIdsEqual(createTicketDtoSet)) {

            return new ResponseEntity<>("Tickets should be for the same show", HttpStatus.BAD_REQUEST);

        }



        // Obtengo el primer ticket

        CreateTicketDto firstTicket = TicketUtils.getFirstTicket(createTicketDtoSet);


        // Verifico que el show existe

        if(!showService.existsById(firstTicket.getShowId())) {

            return new ResponseEntity<>("Show not found", HttpStatus.CONFLICT);
        }


        // Busco el show seleccionado

        Show showSelected = showService.getShow(firstTicket.getShowId());



        String seatAlreadyTaken = ticketService.checkSeatTaken(createTicketDtoSet);
        if(!"All seats are valid.".equals(seatAlreadyTaken)){

            return new ResponseEntity<>(seatAlreadyTaken, HttpStatus.CONFLICT);
        }

        // Verifico que los asientos seleccionados corresponden a la sala


        String checkSeatAndRoom = ticketService.validateSeatsAndRoom(createTicketDtoSet);

        if(!"All seats are valid.".equals(checkSeatAndRoom)){
            return new ResponseEntity<>(checkSeatAndRoom, HttpStatus.CONFLICT);
        }


        Client clientAuth = clientService.get_full_client(authentication);





        return new ResponseEntity<>(ticketService.saveTickets(createTicketDtoSet, clientAuth, showSelected), HttpStatus.CREATED);

    }


    @PatchMapping("/api/current/change_tickets")
    public ResponseEntity<String> change_tickets(Authentication authentication, @RequestBody Set<ChangeTicketsDto> changeTicketDtoSet, @RequestParam Long ShowId, @RequestParam Long PurchaseId) {

        return new ResponseEntity<>("sup", HttpStatus.CREATED);
    }

}
