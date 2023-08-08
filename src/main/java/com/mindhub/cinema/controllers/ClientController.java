package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.models_dtos.ClientDto;
import com.mindhub.cinema.dtos.param_dtos.ChangeEmailDto;
import com.mindhub.cinema.dtos.param_dtos.ChangePasswordDto;
import com.mindhub.cinema.dtos.param_dtos.RegisterClientDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import com.mindhub.cinema.utils.apiUtils.ValidationUtils;
import com.mindhub.cinema.utils.enums.ClientRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;




@RestController
public class ClientController {

// Rutas de acceso público /api
// Rutas de acceso cliente registrado /api/current
// Rutas de acceso empleado /api/employee
// Rutas de acceso propietario /api/admin


    @Autowired
    ClientServiceInterface clientService;

    @Autowired
    PasswordEncoder passwordEncoder;


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
            return new ResponseEntity<>("Email is not valid. Req example@domain.com", HttpStatus.BAD_REQUEST);

        }

        // Validar que el password cumpla con los requisitos


        if (ValidationUtils.checkInvalidPassword(registerClientDto.getPassword())) {
            return new ResponseEntity<>("The password should be at least 8 characters long and include 1 uppercase, 1 lowercase, 1 number and 1 symbol", HttpStatus.BAD_REQUEST);
        }





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


    @GetMapping("/api/isAuthenticated")
    public Boolean isAuthenticated (Authentication authentication){
        if(authentication != null){
            return true;
        } else {
            return false;
        }
    }


    @PatchMapping("/api/current/password")
    public ResponseEntity<Object> changePassword(Authentication authentication, @RequestBody ChangePasswordDto changePasswordDto){

        if(ValidationUtils.checkInvalidPassword(changePasswordDto.getNewPassword())){
            return new ResponseEntity<>("New Password should contain at least 1 Uppercase, 1 LowerCase, 1 Number and 1 Symbol", HttpStatus.CONFLICT);
        }

        Client client = clientService.get_full_client(authentication);

        String currentPw = changePasswordDto.getCurrentPassword();
        String clientPw = client.getPassword();

        if(passwordEncoder.matches(currentPw, clientPw)){
            return new ResponseEntity<>("Current passwords match", HttpStatus.OK);
        }









            clientService.changePassword(client, changePasswordDto.getNewPassword());
            return new ResponseEntity<>("Password changed", HttpStatus.OK);




    }



    @PatchMapping("/api/current/email")
    public ResponseEntity<Object> changeEmail(Authentication authentication, @RequestBody ChangeEmailDto changeEmailDto){

        Client client = clientService.get_full_client(authentication);

        if(!ValidationUtils.compareStrings(client.getEmail(), changeEmailDto.getCurrentEmail())){
            return new ResponseEntity<>("Current email do not match", HttpStatus.CONFLICT);
        }

        if(ValidationUtils.compareStrings(client.getEmail(), changeEmailDto.getNewEmail())){
            return new ResponseEntity<>("Current and new email should be different", HttpStatus.CONFLICT);
        }

        if(!ValidationUtils.checkValidEmail(changeEmailDto.getNewEmail()).matches()){
            return new ResponseEntity<>("Email is not valid. Req example@domain.com", HttpStatus.BAD_REQUEST);

        }

        else {
            clientService.changeEmail(client, changeEmailDto.getNewEmail());


            return new ResponseEntity<>("Email updated", HttpStatus.OK);
        }

    }





}
