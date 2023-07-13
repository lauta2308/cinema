package com.mindhub.cinema.services.servinterfaces;

import org.springframework.http.ResponseEntity;

public interface ClientServiceInterface {
    ResponseEntity<String> registerClient(String name, String lastName, String email, String password, String bornDate);
}
