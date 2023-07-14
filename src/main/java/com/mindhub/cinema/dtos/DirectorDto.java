package com.mindhub.cinema.dtos;

import com.mindhub.cinema.models.Director;

import java.util.Set;
import java.util.stream.Collectors;

public class DirectorDto {


    private long id;

    private String name;

    private String lastName;




    public DirectorDto(Director director) {
        this.id = director.getId();
        this.name = director.getName();
        this.lastName = director.getLastName();

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


}
