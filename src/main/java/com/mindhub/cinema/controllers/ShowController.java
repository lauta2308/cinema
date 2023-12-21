package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.models_dtos.ShowDto;
import com.mindhub.cinema.services.servinterfaces.ShowServiceInterface;
import com.mindhub.cinema.utils.apiUtils.ValidationUtils;
import com.mindhub.cinema.utils.enums.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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



    @GetMapping("/api/admin/get_shows")
    public Object get_shows(Authentication authentication){

        if(authentication == null){
            return new ResponseEntity<>("Login first", HttpStatus.FORBIDDEN);
        }

        if(ValidationUtils.checkUserRole(authentication) != "ADMIN"){
            return new ResponseEntity<>("Not an admin", HttpStatus.CONFLICT);
        }
        return showService.get_shows();
    }







}
