package com.mindhub.cinema.repositories;


import com.mindhub.cinema.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.Set;

@RepositoryRestResource
public interface ShowRepository extends JpaRepository<Show, Long> {


    // Método para obtener los shows que tengan un movieId específico y cuya startTime sea posterior a la hora actual.
    Set<Show> findByMovie_IdAndStartTimeAfter(Long movieId, LocalDateTime startTime);
}


