package com.mindhub.cinema.repositories;

import com.mindhub.cinema.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DirectorRepository extends JpaRepository<Director, Long> {
}
