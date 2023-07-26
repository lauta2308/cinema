package com.mindhub.cinema.models;

import com.mindhub.cinema.utils.enums.MovieAvailability;
import com.mindhub.cinema.utils.enums.MovieGenre;
import com.mindhub.cinema.utils.enums.MovieRestriction;
import com.mindhub.cinema.utils.enums.MovieType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String movieImg;


    private String movieTrailer;

    private String name;

    private String description;

    private MovieRestriction movieRestriction;

    private Integer duration;

    private String languaje;

    private MovieGenre movieGenre;

    private Integer timesPlayed = 0;

    private Integer ticketsSold = 0;

    private MovieType movieType;

    private MovieAvailability movieAvailability;

    @OneToMany(mappedBy="movie", fetch=FetchType.EAGER)
    private Set<Show> shows = new HashSet<>();


    public Movie() {
    }

    public Movie(String movieImg, String movieTrailer, String name, String description, MovieRestriction movieRestriction, Integer duration, String languaje, MovieGenre movieGenre, MovieType movieType, MovieAvailability movieAvailability) {
        this.movieImg = movieImg;
        this.movieTrailer = movieTrailer;
        this.name = name;
        this.description = description;
        this.movieRestriction = movieRestriction;
        this.duration = duration;
        this.languaje = languaje;
        this.movieGenre = movieGenre;
        this.movieType = movieType;
        this.movieAvailability = movieAvailability;
    }

    public long getId() {
        return id;
    }

    public String getMovieImg() {
        return movieImg;
    }

    public void setMovieImg(String movieImg) {
        this.movieImg = movieImg;
    }

    public String getMovieTrailer() {
        return movieTrailer;
    }

    public void setMovieTrailer(String movieTrailer) {
        this.movieTrailer = movieTrailer;
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

    public String getLanguaje() {
        return languaje;
    }

    public void setLanguaje(String languaje) {
        this.languaje = languaje;
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

    public MovieAvailability getMovieAvailability() {
        return movieAvailability;
    }

    public void setMovieAvailability(MovieAvailability movieAvailability) {
        this.movieAvailability = movieAvailability;
    }


    public Set<Show> getShows() {
        return shows;
    }

    public void setShows(Set<Show> shows) {
        this.shows = shows;
    }
}
