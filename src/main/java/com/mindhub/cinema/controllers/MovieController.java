package com.mindhub.cinema.controllers;

import com.mindhub.cinema.dtos.MovieDto;
import com.mindhub.cinema.services.servinterfaces.MovieServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class MovieController {

    @Autowired
    MovieServiceInterface movieService;

    @GetMapping("/api/all_movies")
    Set<MovieDto> get_all_movies(){
        return movieService.getAllMovies();
    }


}
