package com.mindhub.cinema.dtos;

import com.mindhub.cinema.models.Actor;

import java.util.Set;
import java.util.stream.Collectors;

public class ActorDto {

    private long id;

    private String name;

    private String lastName;

    private Set<MovieDto> movies;

    public ActorDto(Actor actor) {
        this.id = actor.getId();
        this.name = actor.getName();
        this.lastName = actor.getLastName();
        this.movies = actor.getMovies().stream().map(movie -> new MovieDto(movie)).collect(Collectors.toSet());
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
