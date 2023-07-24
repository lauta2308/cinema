package com.mindhub.cinema.repositories;


import com.mindhub.cinema.models.Movie;
import com.mindhub.cinema.utils.enums.MovieAvailability;
import com.mindhub.cinema.utils.enums.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Set;
import java.util.stream.DoubleStream;

@RepositoryRestResource
public interface MovieRepository extends JpaRepository<Movie, Long> {


    boolean existsByNameAndMovieType(String name, MovieType movieType);


    Set<Movie> findByMovieAvailabilityOrMovieAvailability(MovieAvailability movieAvailability1, MovieAvailability movieAvailability2);
}
