package com.mindhub.cinema.dtos.param_dtos;

import com.mindhub.cinema.utils.enums.MovieAvailability;
import com.mindhub.cinema.utils.enums.MovieGenre;
import com.mindhub.cinema.utils.enums.MovieRestriction;
import com.mindhub.cinema.utils.enums.MovieType;

public class CreateMovieDto {

    private String movieImg;

    private String movieTrailer;

    private String name;

    private String description;

    private MovieRestriction movieRestriction;

    private Integer duration;

    private String languaje;

    private MovieGenre movieGenre;

    private MovieType movieType;

    private MovieAvailability movieAvailability;

    public CreateMovieDto(String movieImg, String movieTrailer, String name, String description, MovieRestriction movieRestriction, Integer duration, String languaje, MovieGenre movieGenre, MovieType movieType, MovieAvailability movieAvailability) {
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

    public MovieType getMovieType() {
        return movieType;
    }

    public MovieAvailability getMovieAvailability() {
        return movieAvailability;
    }
}
