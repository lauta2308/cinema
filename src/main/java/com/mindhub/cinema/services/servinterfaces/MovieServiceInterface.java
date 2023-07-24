package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.param_dtos.CreateMovieDto;
import com.mindhub.cinema.dtos.models_dtos.MovieDto;
import com.mindhub.cinema.utils.enums.MovieType;

import java.util.Set;

public interface MovieServiceInterface {

    Set<MovieDto> getMoviesOnSchedule();

    void add_movie(CreateMovieDto createMovieDto);

    boolean existsBYNameAndMovieType(String name, MovieType movieType);


}
