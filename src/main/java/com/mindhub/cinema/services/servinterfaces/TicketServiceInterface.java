package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.models.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface TicketServiceInterface {
    Set<Ticket> getTicketsTaken(Long showId);

    Ticket checkDuplicateTicket(Long seatId, Show showSelected);

    ResponseEntity<String> saveTicket(Long seatId, Integer seatPlace, Purchase purchaseParam, Show showSelected);
}
