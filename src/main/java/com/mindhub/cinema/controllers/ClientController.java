package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.ClientDto;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClientController {

// Rutas de acceso público /api
// Rutas de acceso cliente registrado /api/current
// Rutas de acceso empleado /api/employee
// Rutas de acceso propietario /api/admin


    @Autowired
    ClientServiceInterface clientService;


    // Register one client

    @PostMapping("/api/register_client")
    public ResponseEntity<String> register_client(

            @RequestParam String name, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String password, @RequestParam String bornDate) {


        return clientService.registerClient(name, lastName, email, password, bornDate);



    }


    // Get authenticated user

    @GetMapping("/api/authenticated_user")
    public ClientDto get_authenticated_user (Authentication authentication){

        return clientService.get_authenticated_user(authentication);

    }







}
