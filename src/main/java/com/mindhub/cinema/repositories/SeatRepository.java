package com.mindhub.cinema.repositories;


import com.mindhub.cinema.dtos.models_dtos.SeatDto;
import com.mindhub.cinema.models.CinemaRoom;
import com.mindhub.cinema.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Set;

@RepositoryRestResource
public interface SeatRepository extends JpaRepository<Seat, Long> {


    boolean existsByIdAndSeatPlaceAndCinemaRoom(Long id, Integer seatPlace, CinemaRoom cinemaRoom);

    List<Seat> findByCinemaRoom_id(Long cinemaRoomId);
}
