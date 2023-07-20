package com.mindhub.cinema.repositories;


import com.mindhub.cinema.models.CinemaRoom;
import com.mindhub.cinema.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Long> {


    boolean existsByRoomName(String roomName);

    CinemaRoom findByShows_Id(Long showId);

}
