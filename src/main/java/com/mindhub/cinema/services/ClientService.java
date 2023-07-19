package com.mindhub.cinema.services;


import com.mindhub.cinema.dtos.ClientDto;
import com.mindhub.cinema.dtos.RegisterClientDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.repositories.ClientRepository;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientService implements ClientServiceInterface {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ClientRepository clientRepository;





    @Override
    public ClientDto get_authenticated_user(Authentication authentication) {
        return new ClientDto(clientRepository.findByEmail(authentication.getName()));
    }

    @Override
    public Client get_full_client(Authentication authentication) {
        return clientRepository.findByEmail(authentication.getName());
    }

    // Método para verificar que el password tenga 8 caracteres y al menos 1 mayus, 1 minuscula, 1 numero y 1 simbolo

    public boolean checkInvalidPassword(String password) {
        // Verificar que el password cumpla con los requisitos (al menos una mayúscula, una minúscula, un número, un símbolo y una longitud mínima de 8 caracteres)
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z\\d@#$%^&+=!]).{8,}$";
        return !password.matches(regex);
    }

    @Override
    public boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    @Override
    public ResponseEntity<String> saveClient(RegisterClientDto registerClientDto) {
        clientRepository.save(new Client(registerClientDto.getName(), registerClientDto.getLastName(), registerClientDto.getEmail(), passwordEncoder.encode(registerClientDto.getPassword()), LocalDate.parse(registerClientDto.getBornDate())));

        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }


    public Matcher checkNumbersAndSymbols(String name){
        // Definir la expresión regular que busca números y símbolos
        String regex = ".*[0-9\\p{Punct}].*";

        // Crear un objeto Pattern con la expresión regular
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(name);
    }

    @Override
    public boolean notValidNameLength(String name) {
        return name.length() <= 2;
    }

    @Override
    public Matcher checkEmail(String email) {
        // Definir la expresión regular para validar el formato de correo electrónico
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        // Crear un objeto Pattern con la expresión regular
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(email);
    }
}
