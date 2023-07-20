package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.CreateTicketDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.models.Ticket;
import com.mindhub.cinema.services.servinterfaces.*;
import com.mindhub.cinema.utils.apiUtils.TicketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


    // Create ticket
    @PostMapping("/api/current/create_ticket")
    public ResponseEntity<String> create_ticket(Authentication authentication, @RequestBody Set<CreateTicketDto> createTicketDtoSet){



        Client clientAuth = clientService.get_full_client(authentication);

        if(clientAuth == null){
            return new ResponseEntity<>("User not found", HttpStatus.CONFLICT);
        }

        if (!TicketUtils.areAllPurchaseIdsEqual(createTicketDtoSet)) {

           return new ResponseEntity<>("All tickets should have the same purchase Id", HttpStatus.BAD_REQUEST);

        }

        if (!TicketUtils.areAllSeatPlacesUnique(createTicketDtoSet)) {

            return new ResponseEntity<>("Seat places of all tickets should be unique", HttpStatus.BAD_REQUEST);

        }

        if (!TicketUtils.areAllshowIdsEqual(createTicketDtoSet)) {

            return new ResponseEntity<>("Tickets should be for the same show", HttpStatus.BAD_REQUEST);

        }



        // Verifico que la compra existe

        CreateTicketDto firstTicket = TicketUtils.getFirstTicket(createTicketDtoSet);


        if(!purchaseService.existsById(firstTicket.getPurchaseId())) {

            return new ResponseEntity<>("Purchase not found", HttpStatus.CONFLICT);
        }


        // verifico que la compra sea del cliente autenticado


        Purchase purchaseParam = purchaseService.findPurchaseById(firstTicket.getPurchaseId());


        if(purchaseParam.getClient().getId() != clientAuth.getId()){
            return new ResponseEntity<>("Purchase and client dont match", HttpStatus.CONFLICT);
        }

        // Verifico que el show existe

        if(!showService.existsById(firstTicket.getShowId())) {

            return new ResponseEntity<>("Show not found", HttpStatus.CONFLICT);
        }


        // Busco el show seleccionado

        Show showSelected = showService.getShow(firstTicket.getShowId());


        if(showSelected == null){
            return new ResponseEntity<>("Could not find the show", HttpStatus.CONFLICT);
        }


        String seatAlreadyTaken = ticketService.checkSeatTaken(createTicketDtoSet);
        if(!"All seats are valid.".equals(seatAlreadyTaken)){

            return new ResponseEntity<>(seatAlreadyTaken, HttpStatus.CONFLICT);
        }

        // Verifico que los asientos seleccionados corresponden a la sala


        String checkSeatAndRoom = ticketService.validateSeatsAndRoom(createTicketDtoSet);

        if(!"All seats are valid.".equals(checkSeatAndRoom)){
            return new ResponseEntity<>(checkSeatAndRoom, HttpStatus.CONFLICT);
        }


        ticketService.saveTickets(createTicketDtoSet, purchaseParam, showSelected);


        return new ResponseEntity<>("Tickets saved", HttpStatus.CREATED);

    }




}
