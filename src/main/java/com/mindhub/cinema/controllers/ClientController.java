package com.mindhub.cinema.controllers;


import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @PostMapping("/api/register_client")
    public ResponseEntity<String> register(

            @RequestParam String name, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String password, @RequestParam String bornDate) {


        return clientService.registerClient(name, lastName, email, password, bornDate);




    }





}
