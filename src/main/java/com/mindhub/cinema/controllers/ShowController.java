package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.models_dtos.ShowDto;
import com.mindhub.cinema.services.servinterfaces.ShowServiceInterface;
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




    @GetMapping("/api/2d_shows")
    public List<ShowDto> get_2d_shows(){
        return showService.get_2d_shows();
    }


    @GetMapping("/api/3d_shows")
    public List<ShowDto> get_3d_shows(){
        return showService.get_3d_shows();
    }







}
