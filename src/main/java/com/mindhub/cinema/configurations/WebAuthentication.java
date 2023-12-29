package com.mindhub.cinema.configurations;

import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.repositories.ClientRepository;
import com.mindhub.cinema.utils.enums.ClientStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    ClientRepository clientRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }


    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {


        auth.userDetailsService(inputName-> {

            Client client = clientRepository.findByEmail(inputName);

            if (client != null) {
                if(client.getClientStatus() == ClientStatus.BANNED){

                    throw new RuntimeException("User banned");
                }
                if (client.getClientRol().toString().contains("ADMIN")){

                    return new User(client.getEmail(), client.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList ("ADMIN"));

                } else if (client.getClientRol().toString().contains("EMPLOYEE")) {

                    return new User(client.getEmail(), client.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList ("EMPLOYEE"));

                } else {


                    return new User(client.getEmail(), client.getPassword(),AuthorityUtils.createAuthorityList("CLIENT"));}

            } else {

                throw new UsernameNotFoundException("Unknown user: " + inputName);

            }

        });

    }


}