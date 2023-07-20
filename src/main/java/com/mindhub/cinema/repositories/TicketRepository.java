package com.mindhub.cinema.repositories;

import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketRepository extends JpaRepository<Ticket, Long> {


    boolean existsBySeatPlaceAndShow_id(Integer seatPlace, Long showId);

}
