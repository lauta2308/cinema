package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.CreateTicketDto;
import com.mindhub.cinema.models.CinemaRoom;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.models.Ticket;
import com.mindhub.cinema.repositories.TicketRepository;
import com.mindhub.cinema.services.servinterfaces.CinemaRoomServiceInterface;
import com.mindhub.cinema.services.servinterfaces.SeatServiceInterface;
import com.mindhub.cinema.services.servinterfaces.TicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class TicketService implements TicketServiceInterface {


    @Autowired
    CinemaRoomServiceInterface cinemaRoomService;

    @Autowired
    SeatServiceInterface seatService;

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Set<Ticket> getTicketsTaken(Long showId) {
      return ticketRepository.findAll().stream().filter(ticket -> ticket.getShow().getId() == showId).collect(Collectors.toSet());
    }


    @Override
    public String checkSeatTaken(Set<CreateTicketDto> createTicketDtoSet) {

        if (createTicketDtoSet == null || createTicketDtoSet.isEmpty()) {
            return "The ticket set is empty.";
        }

        Set<String> takenSeats = new HashSet<>();
        StringBuilder message = new StringBuilder();

        for (CreateTicketDto ticketDto : createTicketDtoSet) {
            Integer seatPlace = ticketDto.getSeatPlace();
            Long showId = ticketDto.getShowId();


            if (ticketRepository.existsBySeatPlaceAndShow_id(seatPlace, showId)) {
                takenSeats.add("SeatPlace: " + seatPlace);
            }
        }

        if (!takenSeats.isEmpty()) {
            message.append("The following seats are already taken: ");
            message.append(String.join(", ", takenSeats));
        } else {
            message.append("All seats are valid.");
        }

        return message.toString();

    }

    @Override
    public String validateSeatsAndRoom(Set<CreateTicketDto> createTicketDtoSet) {

        if (createTicketDtoSet == null || createTicketDtoSet.isEmpty()) {
            return "The ticket set is empty.";
        }

        CinemaRoom cinemaRoom = cinemaRoomService.findByShow_Id(createTicketDtoSet.stream().findFirst().get().getShowId());

        StringBuilder message = new StringBuilder();

        for (CreateTicketDto ticketDto : createTicketDtoSet) {
            Long seatId = ticketDto.getSeatId();
            Integer seatPlace = ticketDto.getSeatPlace();

            boolean seatExists = seatService.existsByIdAndSeatPlaceAndCinemaRoom(seatId, seatPlace, cinemaRoom);
            if (!seatExists) {
                message.append("The seat with SeatId: ").append(seatId)
                        .append(", SeatPlace: ").append(seatPlace)
                        .append(" does not belong to the CinemaRoom: ").append(cinemaRoom.getId())
                        .append(". ");
            }
        }

        if (message.length() == 0) {
            message.append("All seats are valid.");
        }

        return message.toString();









    }

    @Override
    @Transactional
    public void saveTickets(Set<CreateTicketDto> createTicketDtoSet, Purchase purchaseParam, Show showSelected) {

        if (createTicketDtoSet != null && !createTicketDtoSet.isEmpty()) {
            for (CreateTicketDto createTicketDto : createTicketDtoSet) {

                ticketRepository.save(new Ticket(createTicketDto.getSeatId(), createTicketDto.getSeatPlace(), purchaseParam, showSelected));
            }
        }





    }


}
