package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.param_dtos.CreateTicketDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.models.Ticket;

import java.util.Set;

public interface TicketServiceInterface {
    Set<Ticket> getTicketsTaken(Long showId);

    String checkSeatTaken(Set<CreateTicketDto> createTicketDtoSet);

    String validateSeatsAndRoom(Set<CreateTicketDto> createTicketDtoSet);


    String saveTickets(Set<CreateTicketDto> createTicketDtoSet, Client client, Show showSelected);
}
