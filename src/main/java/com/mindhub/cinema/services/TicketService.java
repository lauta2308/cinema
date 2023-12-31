package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.models_dtos.PurchaseDto;
import com.mindhub.cinema.dtos.models_dtos.TicketDto;
import com.mindhub.cinema.dtos.param_dtos.CreateTicketDto;
import com.mindhub.cinema.models.*;
import com.mindhub.cinema.repositories.PurchaseRepository;
import com.mindhub.cinema.repositories.TicketRepository;
import com.mindhub.cinema.services.servinterfaces.CinemaRoomServiceInterface;
import com.mindhub.cinema.services.servinterfaces.SeatServiceInterface;
import com.mindhub.cinema.services.servinterfaces.TicketServiceInterface;
import com.mindhub.cinema.utils.enums.ClientRol;
import com.mindhub.cinema.utils.enums.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class TicketService implements TicketServiceInterface {



    @Autowired
    CinemaRoomServiceInterface cinemaRoomService;



    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    SeatServiceInterface seatService;

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Set<Ticket> getTicketsTaken(Long showId) {
      return ticketRepository.findByShow_idAndTicketStatus(showId, TicketStatus.TAKEN);
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


            if (ticketRepository.existsBySeatPlaceAndShow_idAndTicketStatus(seatPlace, showId, TicketStatus.TAKEN)) {
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
            Long seatId = ticketDto.getId();
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
    public String saveTickets(Set<CreateTicketDto> createTicketDtoSet, Client client, Show showSelected) {

       Purchase purchase;

       if(client.getClientRol() == ClientRol.CLIENT){


          purchase =  purchaseRepository.save(new Purchase(client));
       } else {
          purchase = purchaseRepository.save(new Purchase());
       }


        Ticket ticket;
        if (createTicketDtoSet != null && !createTicketDtoSet.isEmpty()) {
            for (CreateTicketDto createTicketDto : createTicketDtoSet) {

               ticket = ticketRepository.save(new Ticket(createTicketDto.getId(), createTicketDto.getSeatPlace(), createTicketDto.getCustomerAge(),purchase, showSelected));

               ticket.updateTicketPriceByAge();
               ticket.addPriceToPurchase();




            }
        }



        return String.valueOf(purchase.getId());

    }

    public void ticketStatusToCancelled(Set<Ticket> tickets){
        for (Ticket ticket : tickets){
            ticket.setTicketStatus(TicketStatus.CANCELLED);
            ticketRepository.save(ticket);
        }


    }

    @Override
    public void changeTickets(Set<CreateTicketDto> createTicketDtoSet, Show showSelected, PurchaseDto purchaseDto) {

        Ticket ticket;
        CreateTicketDto createTicketDto;

        List<CreateTicketDto> createTicketDtoList = new ArrayList<>(createTicketDtoSet);




        for (int i = 0; i < createTicketDtoList.size(); i++) {
            System.out.println(i);

            createTicketDto = createTicketDtoList.get(i);

            ticket = ticketRepository.findById(createTicketDto.getTicketId()).get();

                ticket.setCustomerAge(createTicketDto.getCustomerAge());
                ticket.setSeatId(createTicketDto.getId());
                ticket.setSeatPlace(createTicketDto.getSeatPlace());
                ticket.setShow(showSelected);

                ticketRepository.save(ticket);
            }

    }

    @Override
    public List<Ticket> findByPurchaseId(long id) {
        return ticketRepository.findByPurchase_id(id);
    }


}
