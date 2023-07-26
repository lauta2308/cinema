package com.mindhub.cinema.dtos.models_dtos;

import com.mindhub.cinema.models.Movie;
import com.mindhub.cinema.utils.enums.MovieAvailability;
import com.mindhub.cinema.utils.enums.MovieGenre;
import com.mindhub.cinema.utils.enums.MovieRestriction;
import com.mindhub.cinema.utils.enums.MovieType;

import java.util.Set;
import java.util.stream.Collectors;

public class MovieDto {

    private long id;

    private String movieImg;

    private String movieTrailer;

    private String name;

    private String description;


    private MovieRestriction movieRestriction;

    private Integer duration;

    private String languaje;

    private MovieGenre movieGenre;

    private Integer timesPlayed;

    private Integer ticketsSold;

    private MovieType movieType;

    private MovieAvailability movieAvailability;




    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.movieImg = movie.getMovieImg();
        this.movieTrailer = movie.getMovieTrailer();
        this.name = movie.getName().toUpperCase();
        this.description = movie.getDescription();
        this.movieRestriction = movie.getMovieRestriction();
        this.duration = movie.getDuration();
        this.languaje = movie.getLanguaje();
        this.movieGenre = movie.getMovieGenre();
        this.timesPlayed = movie.getTimesPlayed();
        this.ticketsSold = movie.getTicketsSold();
        this.movieType = movie.getMovieType();
        this.movieAvailability = movie.getMovieAvailability();

    }

    public long getId() {
        return id;
    }

    public String getMovieImg() {
        return movieImg;
    }

    public String getMovieTrailer() {
        return movieTrailer;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public MovieRestriction getMovieRestriction() {
        return movieRestriction;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getLanguaje() {
        return languaje;
    }

    public MovieGenre getMovieGenre() {
        return movieGenre;
    }

    public Integer getTimesPlayed() {
        return timesPlayed;
    }

    public Integer getTicketsSold() {
        return ticketsSold;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public MovieAvailability getMovieAvailability() {
        return movieAvailability;
    }


}
