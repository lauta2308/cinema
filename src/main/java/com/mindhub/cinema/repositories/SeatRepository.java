package com.mindhub.cinema.repositories;


import com.mindhub.cinema.models.CinemaRoom;
import com.mindhub.cinema.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SeatRepository extends JpaRepository<Seat, Long> {


    boolean existsByIdAndSeatPlaceAndCinemaRoom(Long id, Integer seatPlace, CinemaRoom cinemaRoom);

}
