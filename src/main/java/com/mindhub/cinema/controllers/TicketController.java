package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.CreateTicketDto;
import com.mindhub.cinema.models.*;
import com.mindhub.cinema.services.servinterfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<String> create_ticket(Authentication authentication, @RequestBody CreateTicketDto createTicketDto){



        Client clientAuth = clientService.get_full_client(authentication);

        if(clientAuth == null){
            return new ResponseEntity<>("User not found", HttpStatus.CONFLICT);
        }


        // Verifico que la compra existe

        if(!purchaseService.existById(createTicketDto.getPurchaseId())) {

            return new ResponseEntity<>("Purchase not found", HttpStatus.CONFLICT);
        }


        // verifico que la compra sea del cliente autenticado


        Purchase purchaseParam = purchaseService.findPurchaseById(createTicketDto.getPurchaseId());


        if(purchaseParam.getClient().getId() != clientAuth.getId()){
            return new ResponseEntity<>("Purchase and client dont match", HttpStatus.CONFLICT);
        }

        // Verifico que la compra existe

        if(!showService.existsById(createTicketDto.getShowId())) {

            return new ResponseEntity<>("Show not found", HttpStatus.CONFLICT);
        }


        // Busco el show seleccionado

        Show showSelected = showService.getShow(createTicketDto.getShowId());


        if(showSelected == null){
            return new ResponseEntity<>("Could not find the show", HttpStatus.CONFLICT);
        }

        Ticket seatAlreadyTaken = ticketService.checkDuplicateTicket(createTicketDto.getSeatId(), showSelected);

        if(seatAlreadyTaken != null){
            return new ResponseEntity<>("Seat already taken, take another", HttpStatus.CONFLICT);
        }


        // Verifico que el id del asiento sea un asiento de la sala



        if(!seatService.checkValidSeatIdPlaceRoom(createTicketDto.getSeatId(), createTicketDto.getSeatPlace(),showSelected.getCinemaRoom())){
            return new ResponseEntity<>("Seat not valid", HttpStatus.CONFLICT);
        }


        return ticketService.saveTicket(createTicketDto.getSeatId(), createTicketDto.getSeatPlace(), purchaseParam, showSelected);


    }




}
