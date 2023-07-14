package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.MovieDto;

import java.util.Set;

public interface MovieServiceInterface {
    Set<MovieDto> getAllMovies();
}
