package com.mindhub.cinema.repositories;


import com.mindhub.cinema.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ShowRepository extends JpaRepository<Show, Long> {
}
