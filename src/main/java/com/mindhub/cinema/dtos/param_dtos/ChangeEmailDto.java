package com.mindhub.cinema.dtos.param_dtos;

public class ChangeEmailDto {

    private String currentEmail;

    private String newEmail;

    public ChangeEmailDto(String currentEmail, String newEmail) {
        this.currentEmail = currentEmail;
        this.newEmail = newEmail;
    }

    public String getCurrentEmail() {
        return currentEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }
}
