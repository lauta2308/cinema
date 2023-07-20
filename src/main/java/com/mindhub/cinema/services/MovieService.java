package com.mindhub.cinema.services;


import com.mindhub.cinema.dtos.CreateMovieDto;
import com.mindhub.cinema.dtos.MovieDto;
import com.mindhub.cinema.models.Movie;
import com.mindhub.cinema.repositories.MovieRepository;
import com.mindhub.cinema.services.servinterfaces.MovieServiceInterface;
import com.mindhub.cinema.utils.enums.MovieAvailability;
import com.mindhub.cinema.utils.enums.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        return movies.stream().map(movie -> new MovieDto(movie)).collect(Collectors.toSet());


    }

    @Override
    public ResponseEntity<String> add_movie(CreateMovieDto createMovieDto) {

        movieRepository.save(new Movie(createMovieDto.getMovieImg(), createMovieDto.getMovieTrailer(), createMovieDto.getName(), createMovieDto.getDescription(), createMovieDto.getMovieRestriction(), createMovieDto.getDuration(), createMovieDto.getLanguaje(), createMovieDto.getMovieGenre(), createMovieDto.getMovieType(), createMovieDto.getMovieAvailability()));

        return new ResponseEntity<>("Movie saved", HttpStatus.CREATED);
    }

    @Override
    public boolean existsBYNameAndMovieType(String name, MovieType movieType) {
        return movieRepository.existsByNameAndMovieType(name, movieType);
    }
}
