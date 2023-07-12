package com.mindhub.cinema.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name;

    private String lastName;


    private String email;

    private String password;

    private ClientRol clientRol;

    private ClientLevel clientLevel;

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Review> reviews = new HashSet<>();


    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Purchase> purchases = new HashSet<>();


    public Client() {
    }

    public Client(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientRol getClientRol() {
        return clientRol;
    }

    public void setClientRol(ClientRol clientRol) {
        this.clientRol = clientRol;
    }

    public ClientLevel getClientLevel() {
        return clientLevel;
    }

    public void setClientLevel(ClientLevel clientLevel) {
        this.clientLevel = clientLevel;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }
}
