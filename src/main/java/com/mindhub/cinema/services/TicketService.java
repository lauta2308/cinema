package com.mindhub.cinema.services;

import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.models.Ticket;
import com.mindhub.cinema.repositories.TicketRepository;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import com.mindhub.cinema.services.servinterfaces.ShowServiceInterface;
import com.mindhub.cinema.services.servinterfaces.TicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class TicketService implements TicketServiceInterface {

    @Autowired
    ClientServiceInterface clientService;

    @Autowired
    PurchaseServiceInterface purchaseService;

    @Autowired
    ShowServiceInterface showService;

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Set<Ticket> getTicketsTaken(Long showId) {
      return ticketRepository.findAll().stream().filter(ticket -> ticket.getShow().getId() == showId).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public ResponseEntity<String> createTicket(Authentication authentication, Long showId, Long seatId, Integer seatPlace) {
        Client clientAuth = clientService.get_full_client(authentication);

        if(clientAuth == null){
            return new ResponseEntity<>("User not found", HttpStatus.CONFLICT);
        }


        // Creo una compra y se la agrego al cliente

        Purchase createPurchaseToClient = purchaseService.addPurchaseToClient(clientAuth);

        if(createPurchaseToClient == null){
            return new ResponseEntity<>("Could not make the purchase", HttpStatus.CONFLICT);
        }

        // Busco el show seleccionado

        Show showSelected = showService.getShow(showId);


        if(showSelected == null){
            return new ResponseEntity<>("Could not find the show", HttpStatus.CONFLICT);
        }

        Ticket seatAlreadyTaken = ticketRepository.findBySeatIdAndShow(seatId, showSelected);

        if(seatAlreadyTaken != null){
            return new ResponseEntity<>("Seat already taken, take another", HttpStatus.CONFLICT);
        }

        Ticket ticket = ticketRepository.save(new Ticket(seatId, seatPlace, createPurchaseToClient, showSelected));

        if(ticket != null){
            return new ResponseEntity<>("Purchase created, ticket added, seat assigned", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Something went wrong", HttpStatus.CONFLICT);
        }
    }
}
