package com.mindhub.cinema.services;


import com.mindhub.cinema.dtos.param_dtos.CreateMovieDto;
import com.mindhub.cinema.dtos.models_dtos.MovieDto;
import com.mindhub.cinema.models.Movie;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.repositories.MovieRepository;
import com.mindhub.cinema.services.servinterfaces.MovieServiceInterface;
import com.mindhub.cinema.utils.apiUtils.MovieUtils;
import com.mindhub.cinema.utils.enums.MovieAvailability;
import com.mindhub.cinema.utils.enums.MovieGenre;
import com.mindhub.cinema.utils.enums.MovieRestriction;
import com.mindhub.cinema.utils.enums.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        movieRepository.save(new Movie(createMovieDto.getMovieImg(), createMovieDto.getMovieTrailer(), createMovieDto.getName(), createMovieDto.getDescription(), createMovieDto.getMovieRestriction(), createMovieDto.getDuration(), createMovieDto.getLanguage(), createMovieDto.getMovieGenre(), createMovieDto.getMovieType(), createMovieDto.getMovieAvailability()));

    }

    @Override
    public boolean existsBYNameAndMovieType(String name, MovieType movieType) {
        return movieRepository.existsByNameAndMovieType(name, movieType);
    }

    @Override
    public MovieDto getMovie(Long movieId) {
        return MovieUtils.movieToDto(movieRepository.findById(movieId).get());
    }

    @Override
    public Movie findByMovieId(Long movieId) {
        return movieRepository.findById(movieId).get();
    }

    @Override
    public Object getMovies() {
        return MovieUtils.allMoviesToDto(movieRepository.findAll());
    }

    @Override
    public void edit_movie(CreateMovieDto createMovieDto, Long movieId) {
        Movie movie = movieRepository.findById(movieId).get();

        movie.setMovieImg(createMovieDto.getMovieImg());

        movie.setMovieTrailer(createMovieDto.getMovieTrailer());

        movie.setName(createMovieDto.getName());

        movie.setDescription(createMovieDto.getDescription());

        movie.setMovieRestriction(createMovieDto.getMovieRestriction());

        movie.setDuration(createMovieDto.getDuration());

        movie.setLanguage(createMovieDto.getLanguage());

        movie.setMovieGenre(createMovieDto.getMovieGenre());

        movie.setMovieType(createMovieDto.getMovieType());

        movie.setMovieAvailability(createMovieDto.getMovieAvailability());

        movieRepository.save(movie);


    }

    @Override
    public boolean existsById(Long movieId) {
        return movieRepository.existsById(movieId);
    }

    @Override
    public void increase_tickets_sold(Long movieId, Integer quantity) {

        Movie movie =     movieRepository.findById(movieId).get();

        movie.addTicketsSold(quantity);


        movieRepository.save(movie);

    }

    @Override
    public void updateMovieTimesPlayed(List<Show> shows) {



        shows.forEach(show -> {
                    Movie movie = movieRepository.findById(show.getMovie().getId()).get();
                    movie.setTimesPlayed(movie.getTimesPlayed() + 1);
                    movieRepository.save(movie);

                }

                );



    }
}
