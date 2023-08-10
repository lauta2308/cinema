package com.mindhub.cinema.dtos.models_dtos;

import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.utils.apiUtils.PurchaseUtils;
import com.mindhub.cinema.utils.apiUtils.ReviewUtils;
import com.mindhub.cinema.utils.enums.ClientLevel;
import com.mindhub.cinema.utils.enums.ClientRol;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDto {

    private long id;

    private String name;

    private String lastName;

    private String email;




    private ClientRol clientRol;

    private ClientLevel clientLevel;

    private List<ReviewDto> reviews;

    private List<PurchaseDto> purchases;


    public ClientDto() {
    }


    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.clientRol = client.getClientRol();
        this.clientLevel = client.getClientLevel();
        this.reviews = ReviewUtils.sortReviewSet(client.getReviews());
        this.purchases = PurchaseUtils.sortPurchaseSet(client.getPurchases());
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




    public ClientRol getClientRol() {
        return clientRol;
    }

    public ClientLevel getClientLevel() {
        return clientLevel;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public List<PurchaseDto> getPurchases() {
        return purchases;
    }
}
