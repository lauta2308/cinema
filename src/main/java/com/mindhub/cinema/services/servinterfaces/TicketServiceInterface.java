package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.CreateTicketDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.models.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface TicketServiceInterface {
    Set<Ticket> getTicketsTaken(Long showId);

    String checkSeatTaken(Set<CreateTicketDto> createTicketDtoSet);

    String validateSeatsAndRoom(Set<CreateTicketDto> createTicketDtoSet);


    String saveTickets(Set<CreateTicketDto> createTicketDtoSet, Client client, Show showSelected);
}
