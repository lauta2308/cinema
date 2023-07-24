package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.models_dtos.ClientDto;
import com.mindhub.cinema.dtos.param_dtos.RegisterClientDto;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import com.mindhub.cinema.utils.apiUtils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

           @RequestBody RegisterClientDto registerClientDto) {



        if(registerClientDto.getName().isBlank() && registerClientDto.getLastName().isBlank() && registerClientDto.getEmail().isBlank() && registerClientDto.getPassword().isBlank() && registerClientDto.getBornDate().isBlank()){

            return new ResponseEntity<>("Empty fields", HttpStatus.BAD_REQUEST);
        }

        // validacion del nombre

        if(ValidationUtils.checkNumbersAndSymbols(registerClientDto.getName()).matches()){

            return new ResponseEntity<>("Name cant contain numbers or symbols", HttpStatus.BAD_REQUEST);
        } else if (ValidationUtils.notValidNameLength(registerClientDto.getName())) {


            return new ResponseEntity<>("Name should be at least 2 characters", HttpStatus.BAD_REQUEST);
        }


        // validacion apellido

        if(ValidationUtils.checkNumbersAndSymbols(registerClientDto.getLastName()).matches()){

            return new ResponseEntity<>("Last Name cant contain numbers or symbols", HttpStatus.BAD_REQUEST);
        } else if (ValidationUtils.notValidNameLength(registerClientDto.getLastName())) {

            return new ResponseEntity<>("Last name should be at least 2 characters", HttpStatus.BAD_REQUEST);
        }

        // validacion nombre y apellido

        if(ValidationUtils.nameAndLastAreSame(registerClientDto.getName(), registerClientDto.getLastName())){
            return new ResponseEntity<>("Name and Last name should be different", HttpStatus.BAD_REQUEST);
        }

        // validacion email

        if(!ValidationUtils.checkValidEmail(registerClientDto.getEmail()).matches()){
            return new ResponseEntity<>("Email is not valid. Req text@domain.com", HttpStatus.BAD_REQUEST);

        }

        // Validar que el password cumpla con los requisitos


        if (ValidationUtils.checkInvalidPassword(registerClientDto.getPassword())) {
            return new ResponseEntity<>("The password should be at least 8 characters long and include 1 uppercase, 1 lowercase, 1 number and 1 symbol", HttpStatus.BAD_REQUEST);
        }

/*
        // Validacion fecha

        if(registerClientDto.getBornDate().isBlank() || registerClientDto.getBornDate() == null){
            return new ResponseEntity<>("Date is empty", HttpStatus.BAD_REQUEST);
        }

        // Validar usuario +15 años


        if(ValidationUtils.clientYoungerThan15(registerClientDto.getBornDate())){
            return new ResponseEntity<>("User should be at least 15 years old", HttpStatus.BAD_REQUEST);
        }
*/



        if(clientService.existsByEmail(registerClientDto.getEmail())) {
            return new ResponseEntity<>("Email already registered", HttpStatus.CONFLICT);
        }  else {
             clientService.saveClient(registerClientDto);

             return new ResponseEntity<>("Client saved", HttpStatus.CREATED);

        }







    }


    // Get authenticated user

    @GetMapping("/api/authenticated_user")
    public ClientDto get_authenticated_user (Authentication authentication){

        return clientService.get_authenticated_user(authentication);

    }







}
