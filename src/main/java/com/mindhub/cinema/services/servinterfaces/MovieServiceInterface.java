package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.CreateMovieDto;
import com.mindhub.cinema.dtos.MovieDto;
import com.mindhub.cinema.utils.enums.MovieType;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface MovieServiceInterface {
    Set<MovieDto> getAllMovies();

    ResponseEntity<String> add_movie(CreateMovieDto createMovieDto);

    boolean existsBYNameAndMovieType(String name, MovieType movieType);
}
