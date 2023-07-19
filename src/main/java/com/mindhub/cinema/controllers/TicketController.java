package com.mindhub.cinema.controllers;


import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.models.Ticket;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import com.mindhub.cinema.services.servinterfaces.ShowServiceInterface;
import com.mindhub.cinema.services.servinterfaces.TicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    // Create ticket
    @PostMapping("/api/current/create_ticket")
    public ResponseEntity<String> create_ticket(Authentication authentication, @RequestParam Long purchaseId, @RequestParam Long showId, @RequestParam Long seatId, @RequestParam Integer seatPlace){



        Client clientAuth = clientService.get_full_client(authentication);

        if(clientAuth == null){
            return new ResponseEntity<>("User not found", HttpStatus.CONFLICT);
        }


        // Verifico que la compra existe

        if(!purchaseService.existById(purchaseId)) {

            return new ResponseEntity<>("Purchase not found", HttpStatus.CONFLICT);
        }


        // verifico que la compra sea del cliente autenticado



        Purchase purchaseParam = purchaseService.findPurchaseById(purchaseId);




        if(purchaseParam.getClient().getId() != clientAuth.getId()){
            return new ResponseEntity<>("Purchase and client dont match", HttpStatus.CONFLICT);
        }

        // Busco el show seleccionado

        Show showSelected = showService.getShow(showId);


        if(showSelected == null){
            return new ResponseEntity<>("Could not find the show", HttpStatus.CONFLICT);
        }

        Ticket seatAlreadyTaken = ticketService.checkDuplicateTicket(seatId,showSelected);

        if(seatAlreadyTaken != null){
            return new ResponseEntity<>("Seat already taken, take another", HttpStatus.CONFLICT);
        }

        return ticketService.saveTicket(seatId, seatPlace, purchaseParam, showSelected);


    }




}
