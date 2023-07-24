package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.models_dtos.MovieDto;
import com.mindhub.cinema.models.Movie;

import java.util.Set;
import java.util.stream.Collectors;

public class MovieUtils {



    public static Set<MovieDto> movieSetToDto(Set<Movie> movies) {

        return movies.stream().map(movie -> new MovieDto(movie)).collect(Collectors.toSet());

    }
}
