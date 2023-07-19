package com.mindhub.cinema.dtos;

import java.time.LocalDate;

public class RegisterClientDto {


    private String name;

    private String lastName;

    private String email;

    private String password;

    private String bornDate;


    public RegisterClientDto(String name, String lastName, String email, String password, String bornDate) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.bornDate = bornDate;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBornDate() {
        return bornDate;
    }
}
