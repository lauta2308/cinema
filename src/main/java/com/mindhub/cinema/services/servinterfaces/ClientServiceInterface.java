package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.models_dtos.ClientDto;
import com.mindhub.cinema.dtos.param_dtos.RegisterClientDto;
import com.mindhub.cinema.models.Client;
import org.springframework.security.core.Authentication;

public interface ClientServiceInterface {
    ClientDto get_authenticated_user(Authentication authentication);

    // only for back usage
    Client get_full_client(Authentication authentication);


    void saveClient(RegisterClientDto registerClientDto);




    boolean existsByEmail(String email);

    void changePassword(Client client, String newPassword);

    void changeEmail(Client client, String newEmail);

}
