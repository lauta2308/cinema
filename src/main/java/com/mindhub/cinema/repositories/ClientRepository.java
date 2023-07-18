package com.mindhub.cinema.repositories;


import com.mindhub.cinema.models.Client;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.function.Function;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    boolean existsByEmail(String email);


}
