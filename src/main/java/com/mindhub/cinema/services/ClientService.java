package com.mindhub.cinema.services;


import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.repositories.ClientRepository;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClientService implements ClientServiceInterface {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public ResponseEntity<String> registerClient(String name, String lastName, String email, String password , String bornDate) {

        if(name.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank() || bornDate.isBlank()){

            return new ResponseEntity<>("Empty fields", HttpStatus.BAD_REQUEST);
        }

        // Validar que el password cumpla con los requisitos

        if (!isValidPassword(password)) {
            return new ResponseEntity<>("The password should be at least 8 characters long and include 1 uppercase, 1 lowercase, 1 number and 1 symbol", HttpStatus.BAD_REQUEST);
        }


        // Busco en la base de datos que no haya un cliente registrado con el mismo email

        Client findClient = clientRepository.findByEmail(email);

        // Si findClient es diferente a nulo, devuelvo error.

        if(findClient != null ) {
            return new ResponseEntity<>("Email already registered", HttpStatus.CONFLICT);
        }

        else {
            Client newClient = new Client(name, lastName, email, passwordEncoder.encode(password) , LocalDate.parse(bornDate));
            clientRepository.save(newClient);
            return new ResponseEntity<>("User created", HttpStatus.CREATED);
        }




    }


    // Método para verificar que el password tenga 8 caracteres y al menos 1 mayus, 1 minuscula, 1 numero y 1 simbolo
    private boolean isValidPassword(String password) {
        // Verificar que el password cumpla con los requisitos (al menos una mayúscula, una minúscula, un número, un símbolo y una longitud mínima de 8 caracteres)
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z\\d@#$%^&+=!]).{8,}$";
        return password.matches(regex);
    }



}
