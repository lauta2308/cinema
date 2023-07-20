package com.mindhub.cinema.repositories;


import com.mindhub.cinema.models.Movie;
import com.mindhub.cinema.utils.enums.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieRepository extends JpaRepository<Movie, Long> {


    boolean existsByNameAndMovieType(String name, MovieType movieType);


}
