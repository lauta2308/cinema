package com.mindhub.cinema.services;


import com.mindhub.cinema.dtos.models_dtos.ClientDto;
import com.mindhub.cinema.dtos.param_dtos.RegisterClientDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.repositories.ClientRepository;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import com.mindhub.cinema.utils.apiUtils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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


    @Override
    public boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    @Override
    public void changePassword(Client client, String newPassword) {
        client.setPassword(passwordEncoder.encode(newPassword));
        clientRepository.save(client);
    }

    @Override
    public void changeEmail(Client client, String newEmail) {
        client.setEmail(newEmail);
        clientRepository.save(client);

    }

    @Override
    public List<ClientDto> get_users() {
        return clientRepository.findAll().stream().map(client -> new ClientDto(client)).collect(Collectors.toList());
    }


    @Override
    public void saveClient(RegisterClientDto registerClientDto) {
        clientRepository.save(new Client(StringUtils.firstLetterUppercase(registerClientDto.getName()), StringUtils.firstLetterUppercase(registerClientDto.getLastName()), registerClientDto.getEmail(), passwordEncoder.encode(registerClientDto.getPassword())));

    }







}
