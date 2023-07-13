package com.mindhub.cinema.dtos;

import com.mindhub.cinema.models.Director;

import java.util.Set;
import java.util.stream.Collectors;

public class DirectorDto {


    private long id;

    private String name;

    private String lastName;

    private Set<MovieDto> movies;


    public DirectorDto(Director director) {
        this.id = director.getId();
        this.name = director.getName();
        this.lastName = director.getLastName();
        this.movies = director.getMovies().stream().map(movie -> new MovieDto(movie)).collect(Collectors.toSet());
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

    public Set<MovieDto> getMovies() {
        return movies;
    }
}
