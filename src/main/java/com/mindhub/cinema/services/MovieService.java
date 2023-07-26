package com.mindhub.cinema.services;


import com.mindhub.cinema.dtos.param_dtos.CreateMovieDto;
import com.mindhub.cinema.dtos.models_dtos.MovieDto;
import com.mindhub.cinema.models.Movie;
import com.mindhub.cinema.repositories.MovieRepository;
import com.mindhub.cinema.services.servinterfaces.MovieServiceInterface;
import com.mindhub.cinema.utils.apiUtils.MovieUtils;
import com.mindhub.cinema.utils.enums.MovieAvailability;
import com.mindhub.cinema.utils.enums.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService implements MovieServiceInterface {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<MovieDto> getMoviesOnSchedule() {


        return MovieUtils.movieSetToDto( movieRepository.findByMovieAvailabilityOrMovieAvailability(MovieAvailability.AVAILABLE, MovieAvailability.COMING_SOON).stream().collect(Collectors.toSet()));


    }

    @Override
    public void add_movie(CreateMovieDto createMovieDto) {

        movieRepository.save(new Movie(createMovieDto.getMovieImg(), createMovieDto.getMovieTrailer(), createMovieDto.getName(), createMovieDto.getDescription(), createMovieDto.getMovieRestriction(), createMovieDto.getDuration(), createMovieDto.getLanguaje(), createMovieDto.getMovieGenre(), createMovieDto.getMovieType(), createMovieDto.getMovieAvailability()));

    }

    @Override
    public boolean existsBYNameAndMovieType(String name, MovieType movieType) {
        return movieRepository.existsByNameAndMovieType(name, movieType);
    }

    @Override
    public MovieDto getMovie(Long movieId) {
        return MovieUtils.movieToDto(movieRepository.findById(movieId).get());
    }
}
