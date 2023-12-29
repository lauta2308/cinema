package com.mindhub.cinema.dtos.param_dtos;

public class UserStatusDto {

    private Long id;

    private String email;

    public UserStatusDto() {
    }

    public UserStatusDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
