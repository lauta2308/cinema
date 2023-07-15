package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.ClientDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface ClientServiceInterface {
    ResponseEntity<String> registerClient(String name, String lastName, String email, String password, String bornDate);

    ClientDto get_authenticated_user(Authentication authentication);

    // only for back usage
    Client get_full_client(Authentication authentication);


    // Check if user is admin

}
