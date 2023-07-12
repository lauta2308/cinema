package com.mindhub.cinema.repositories;

import com.mindhub.cinema.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ActorRepository extends JpaRepository<Actor, Long> {

}
