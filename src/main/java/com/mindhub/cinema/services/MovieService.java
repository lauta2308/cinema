package com.mindhub.cinema.services;


import com.mindhub.cinema.dtos.MovieDto;
import com.mindhub.cinema.models.Movie;
import com.mindhub.cinema.repositories.MovieRepository;
import com.mindhub.cinema.services.servinterfaces.MovieServiceInterface;
import com.mindhub.cinema.utils.enums.MovieAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService implements MovieServiceInterface {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public Set<MovieDto> getAllMovies() {
        Set<Movie> movies = movieRepository.findAll().stream().filter(movie -> movie.getMovieAvailability() == MovieAvailability.AVAILABLE || movie.getMovieAvailability() == MovieAvailability.COMING_SOON).collect(Collectors.toSet());

        Set<MovieDto> moviesDto = movies.stream().map(movie -> new MovieDto(movie)).collect(Collectors.toSet());

        return moviesDto;
    }
}
