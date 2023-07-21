package com.mindhub.cinema.dtos.models_dtos;

import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.utils.enums.ClientLevel;
import com.mindhub.cinema.utils.enums.ClientRol;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDto {

    private long id;

    private String name;

    private String lastName;

    private String email;

    private LocalDate bornDate;

    private Integer age;

    private ClientRol clientRol;

    private ClientLevel clientLevel;

    private Set<ReviewDto> reviews;

    private Set<PurchaseDto> purchases;


    public ClientDto() {
    }


    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.bornDate = client.getBornDate();
        this.age = LocalDate.now().getYear() - client.getBornDate().getYear();
        this.clientRol = client.getClientRol();
        this.clientLevel = client.getClientLevel();
        this.reviews = client.getReviews().stream().map(review -> new ReviewDto(review)).collect(Collectors.toSet());
        this.purchases = client.getPurchases().stream().map(purchase -> new PurchaseDto(purchase)).collect(Collectors.toSet());
    }


    public long getId() {
        return id;
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

    public LocalDate getBornDate() {
        return bornDate;
    }

    public Integer getAge() {
        return age;
    }

    public ClientRol getClientRol() {
        return clientRol;
    }

    public ClientLevel getClientLevel() {
        return clientLevel;
    }

    public Set<ReviewDto> getReviews() {
        return reviews;
    }

    public Set<PurchaseDto> getPurchases() {
        return purchases;
    }
}
