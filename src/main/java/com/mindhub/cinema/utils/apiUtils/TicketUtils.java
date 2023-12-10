package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.models_dtos.PurchaseDto;
import com.mindhub.cinema.dtos.models_dtos.ShowDto;
import com.mindhub.cinema.dtos.models_dtos.TicketDto;
import com.mindhub.cinema.dtos.param_dtos.CreateTicketDto;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.models.Ticket;
import com.mindhub.cinema.repositories.TicketRepository;
import com.mindhub.cinema.utils.enums.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicketUtils {


    @Autowired
    TicketRepository ticketRepository;

    public static boolean allElementsHaveTicketId(Set<CreateTicketDto> tickets){
        if (tickets == null || tickets.isEmpty()) {
            return false;
        }


        for (CreateTicketDto ticket : tickets) {
            if (ticket.getTicketId() == null) {
                return false; // Si un ticket no viene con ticket id
            }

        }

        return true;

    }

    public static boolean movieTypeAreSame(Show showSelected, PurchaseDto purchaseDto){
        ShowDto originalShow = purchaseDto.getTickets().stream().findFirst().get().getShow();
        if (showSelected == null || originalShow == null) {
            return false;
        }

        return showSelected.getMovie().getMovieType() == originalShow.getMovie().getMovieType();

    }


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


    public static Set<TicketDto> ticketRequest(Set<CreateTicketDto> createTicketDtoSet, Show show){

        Set<TicketDto> ticketDtoSet = new HashSet<>();
        Ticket ticket;
        for (CreateTicketDto createTicketDto : createTicketDtoSet){
            ticket = new Ticket(createTicketDto.getId(), createTicketDto.getSeatPlace(), createTicketDto.getCustomerAge(), show);
            ticket.updateTicketPriceByAge();
            ticketDtoSet.add(TicketUtils.ticketToDto(ticket));

        }

        return ticketDtoSet;


    }

    public static TicketDto ticketToDto(Ticket ticket){
        return new TicketDto(ticket);
    }



}
