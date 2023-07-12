package com.mindhub.cinema.models;


import com.mindhub.cinema.utils.enums.ClientLevel;
import com.mindhub.cinema.utils.enums.ClientRol;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    private LocalDate bornDate;

    private ClientRol clientRol = ClientRol.CLIENT;

    private ClientLevel clientLevel = ClientLevel.BASIC;

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Review> reviews = new HashSet<>();


    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Purchase> purchases = new HashSet<>();


    public Client() {
    }

    public Client(String name, String lastName, String email, String password, LocalDate bornDate) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.bornDate = bornDate;
    }

    public long getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
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
