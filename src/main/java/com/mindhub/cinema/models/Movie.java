package com.mindhub.cinema.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name;

    private String description;

    private MovieRestriction movieRestriction;

    private Integer duration;

    private MovieGenre movieGenre;

    private Integer timesPlayed;

    private Integer ticketsSold;

    private MovieType movieType;

    @ManyToMany
    Set<Actor> actors;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="director_id")
    private Director director;


    public Movie() {
    }

    public Movie(String name, String description, MovieRestriction movieRestriction, Integer duration, MovieGenre movieGenre, MovieType movieType, Set<Actor> actors, Director director) {
        this.name = name;
        this.description = description;
        this.movieRestriction = movieRestriction;
        this.duration = duration;
        this.movieGenre = movieGenre;
        this.movieType = movieType;
        this.actors = actors;
        this.director = director;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MovieRestriction getMovieRestriction() {
        return movieRestriction;
    }

    public void setMovieRestriction(MovieRestriction movieRestriction) {
        this.movieRestriction = movieRestriction;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public MovieGenre getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(MovieGenre movieGenre) {
        this.movieGenre = movieGenre;
    }

    public Integer getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(Integer timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public Integer getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(Integer ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
}
