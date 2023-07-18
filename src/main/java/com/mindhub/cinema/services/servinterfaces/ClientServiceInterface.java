package com.mindhub.cinema.services.servinterfaces;

import com.jayway.jsonpath.Criteria;
import com.mindhub.cinema.dtos.ClientDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Purchase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.regex.Matcher;

public interface ClientServiceInterface {
    ClientDto get_authenticated_user(Authentication authentication);

    // only for back usage
    Client get_full_client(Authentication authentication);


     boolean checkInvalidPassword(String password);




    ResponseEntity<String> saveClient(String name, String lastName, String email, String password, String bornDate);



    Matcher checkNumbersAndSymbols(String name);

    boolean notValidNameLength(String name);

    Matcher checkEmail(String email);

    boolean existsByEmail(String email);
}
