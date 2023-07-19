package com.mindhub.cinema.controllers;

import com.mindhub.cinema.dtos.CreateMovieDto;
import com.mindhub.cinema.dtos.MovieDto;
import com.mindhub.cinema.services.servinterfaces.MovieServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @PostMapping("/api/admin/add_movie")
    public ResponseEntity<String> add_movie(Authentication authentication, @RequestBody CreateMovieDto createMovieDto){

        if(authentication == null){
            return new ResponseEntity<>("Login first", HttpStatus.FORBIDDEN);
        }

        if(!authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ADMIN"))){
            return new ResponseEntity<>("Not an admin", HttpStatus.CONFLICT);
        }



        if(createMovieDto.getMovieAvailability().toString().isBlank() && createMovieDto.getMovieGenre().toString().isBlank() && createMovieDto.getMovieRestriction().toString().isBlank() && createMovieDto.getMovieTrailer().isBlank() && createMovieDto.getMovieImg().isBlank() && createMovieDto.get)

        return new ResponseEntity<String>("test", HttpStatus.CREATED);
    }

}
