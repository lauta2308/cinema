package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.models_dtos.ShowDto;
import com.mindhub.cinema.services.servinterfaces.ShowServiceInterface;
import com.mindhub.cinema.utils.enums.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShowController {


    @Autowired
    ShowServiceInterface showService;




    @GetMapping("/api/movie_shows")
    public List<ShowDto> movie_shows(@RequestParam Long movieId){


        return showService.get_movie_shows(movieId);
    }




    @GetMapping("/api/showtimes")
    public List<ShowDto> get_showTimes(@RequestParam MovieType movieType){
        return showService.get_showTimes(movieType);
    }









}
