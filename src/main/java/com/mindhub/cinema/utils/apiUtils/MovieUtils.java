package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.models_dtos.MovieDto;
import com.mindhub.cinema.models.Movie;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

public class MovieUtils {


    public static List<MovieDto> movieSetToDto(Set<Movie> movies) {

        List<MovieDto> moviesList = movies.stream().map(movie -> new MovieDto(movie)).collect(Collectors.toList());


        moviesList.sort(Comparator.comparing(MovieDto::getName));

        return moviesList;
    }

    public static MovieDto movieToDto(Movie movie){
        return new MovieDto(movie);
    }

    public static Object allMoviesToDto(List<Movie> movies) {

       return movies.stream().map(movie -> new MovieDto(movie)).collect(Collectors.toList());

    }
}
