package com.mindhub.cinema.controllers;

import com.mindhub.cinema.dtos.CreateMovieDto;
import com.mindhub.cinema.dtos.MovieDto;
import com.mindhub.cinema.services.servinterfaces.MovieServiceInterface;
import com.mindhub.cinema.utils.apiUtils.ValidationUtils;
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

        if(!ValidationUtils.validateAdmin(authentication)){
            return new ResponseEntity<>("Not an admin", HttpStatus.CONFLICT);
        }

        
        if(createMovieDto.getMovieImg().isBlank() && createMovieDto.getMovieTrailer().isBlank() && createMovieDto.getName().isBlank() && createMovieDto.getDescription().isBlank() && createMovieDto.getMovieRestriction().toString().isBlank() && createMovieDto.getDuration().toString().isBlank() && createMovieDto.getLanguaje().isBlank() && createMovieDto.getMovieGenre().toString().isBlank() && createMovieDto.getMovieType().toString().isBlank() && createMovieDto.getMovieAvailability().toString().isBlank() ){
            return new ResponseEntity<>("Empty fields", HttpStatus.BAD_REQUEST);
        }

        if(createMovieDto.getMovieImg().isBlank()){
            return new ResponseEntity<>("Add image", HttpStatus.BAD_REQUEST);
        }

        if(createMovieDto.getMovieTrailer().isBlank()){
            return new ResponseEntity<>("Add trailer", HttpStatus.BAD_REQUEST);
        }

        if(createMovieDto.getName().isBlank()){
            return new ResponseEntity<>("Add name", HttpStatus.BAD_REQUEST);
        }

        if(createMovieDto.getDescription().isBlank()){
            return new ResponseEntity<>("Add description", HttpStatus.BAD_REQUEST);
        }

        if(createMovieDto.getMovieRestriction().toString().isBlank()){
            return new ResponseEntity<>("Add movie restriction", HttpStatus.BAD_REQUEST);
        }

        if(createMovieDto.getDuration().toString().isBlank()){
            return new ResponseEntity<>("Add duration", HttpStatus.BAD_REQUEST);
        }

        if(createMovieDto.getLanguaje().isBlank()){
            return new ResponseEntity<>("Add languaje", HttpStatus.BAD_REQUEST);
        }

        if(createMovieDto.getMovieGenre().toString().isBlank()){
            return new ResponseEntity<>("Add movie genre", HttpStatus.BAD_REQUEST);
        }

        if(createMovieDto.getMovieType().toString().isBlank()){
            return new ResponseEntity<>("Add movie type", HttpStatus.BAD_REQUEST);
        }

        if(createMovieDto.getMovieAvailability().toString().isBlank()){
            return new ResponseEntity<>("Add movie availability", HttpStatus.BAD_REQUEST);
        }



        if(movieService.existsBYNameAndMovieType(createMovieDto.getName(), createMovieDto.getMovieType())){
            return new ResponseEntity<>("Movie name and type already exist", HttpStatus.CONFLICT);
        }

        movieService.add_movie(createMovieDto);

        return new ResponseEntity<>("Movie saved", HttpStatus.CREATED);


    }

}
