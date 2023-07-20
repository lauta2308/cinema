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
    public Ticket checkDuplicateTicket(Long seatId, Show showSelected) {
        return ticketRepository.findBySeatIdAndShow(seatId, showSelected);
    }

    @Override
    @Transactional
    public void saveTicket(Long seatId, Integer seatPlace, Purchase purchaseParam, Show showSelected) {
        ticketRepository.save(new Ticket(seatId, seatPlace, purchaseParam, showSelected));


    }




}
