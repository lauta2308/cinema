package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.ClientDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.repositories.ClientRepository;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
public class ClientController {

// Rutas de acceso público /api
// Rutas de acceso cliente registrado /api/current
// Rutas de acceso empleado /api/employee
// Rutas de acceso propietario /api/admin


    @Autowired
    ClientServiceInterface clientService;

    @Autowired
    ClientRepository clientRepository;

    // Register one client

    @PostMapping("/api/register_client")
    public ResponseEntity<String> register_client(

            @RequestParam String name, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String password, @RequestParam String bornDate) {



        if(name.isBlank() && lastName.isBlank() && email.isBlank() && password.isBlank() && bornDate.isBlank()){

            return new ResponseEntity<>("Empty fields", HttpStatus.BAD_REQUEST);
        }

        // validacion del nombre

        if(clientService.checkNumbersAndSymbols(name).matches()){

            return new ResponseEntity<>("Name cant contain numbers or symbols", HttpStatus.BAD_REQUEST);
        } else if (clientService.notValidNameLength(name)) {

            return new ResponseEntity<>("Name should be at least 2 characters", HttpStatus.BAD_REQUEST);
        }


        // validacion apellido

        if(clientService.checkNumbersAndSymbols(lastName).matches()){

            return new ResponseEntity<>("Last Name cant contain numbers or symbols", HttpStatus.BAD_REQUEST);
        } else if (clientService.notValidNameLength(lastName)) {

            return new ResponseEntity<>("Last name should be at least 2 characters", HttpStatus.BAD_REQUEST);
        }

        // validacion email

        if(!clientService.checkEmail(email).matches()){
            return new ResponseEntity<>("Email is not valid. Req text@domain.com", HttpStatus.BAD_REQUEST);

        }

        // Validar que el password cumpla con los requisitos

        if (clientService.checkInvalidPassword(password)) {
            return new ResponseEntity<>("The password should be at least 8 characters long and include 1 uppercase, 1 lowercase, 1 number and 1 symbol", HttpStatus.BAD_REQUEST);
        }



        if(clientRepository.existsByEmail(email)) {
            return new ResponseEntity<>("Email already registered", HttpStatus.CONFLICT);
        }  else {
            return clientService.saveClient(name, lastName, email, password, bornDate);

        }







    }


    // Get authenticated user

    @GetMapping("/api/authenticated_user")
    public ClientDto get_authenticated_user (Authentication authentication){

        return clientService.get_authenticated_user(authentication);

    }







}
