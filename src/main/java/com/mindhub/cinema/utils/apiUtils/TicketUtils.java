package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.param_dtos.CreateTicketDto;
import com.mindhub.cinema.models.Ticket;
import com.mindhub.cinema.repositories.TicketRepository;
import com.mindhub.cinema.utils.enums.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

public class TicketUtils {


    @Autowired
    TicketRepository ticketRepository;


    public static boolean areAllSeatPlacesUnique(Set<CreateTicketDto> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            return false;
        }

        Set<Integer> seatPlaces = new HashSet<>();
        for (CreateTicketDto ticket : tickets) {
            if (seatPlaces.contains(ticket.getSeatPlace())) {
                return false; // Si el seatPlace ya existe en el conjunto, no son únicos
            }
            seatPlaces.add(ticket.getSeatPlace());
        }

        return true;
    }



    public static boolean areAllshowIdsEqual(Set<CreateTicketDto> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            return false;
        }

        Long showId = null;
        for (CreateTicketDto ticket : tickets) {
            if (showId == null) {
                showId = ticket.getShowId();
            } else if (!showId.equals(ticket.getShowId())) {
                return false;
            }
        }

        return true;


    }

    public static CreateTicketDto getFirstTicket(Set<CreateTicketDto> createTicketDtoSet) {

        return createTicketDtoSet.stream().findFirst().get();
    }


}
