package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.ShowDto;
import com.mindhub.cinema.services.servinterfaces.ShowServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ShowController {


    @Autowired
    ShowServiceInterface showService;




    @GetMapping("/api/movie_shows")
    public Set<ShowDto> movie_shows(@RequestParam Long movieId){


        return showService.get_movie_shows(movieId);
    }








}
