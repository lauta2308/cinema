package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.models.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface TicketServiceInterface {
    Set<Ticket> getTicketsTaken(Long showId);

    ResponseEntity<String> createTicket(Authentication authentication, Long showId, Long seatId, Integer seatPlace);
}
