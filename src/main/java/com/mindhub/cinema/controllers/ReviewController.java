package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.models_dtos.ReviewDto;
import com.mindhub.cinema.dtos.param_dtos.CreateReviewDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Movie;
import com.mindhub.cinema.services.ReviewService;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import com.mindhub.cinema.services.servinterfaces.ReviewServiceInterface;
import com.mindhub.cinema.utils.apiUtils.ReviewUtils;
import com.mindhub.cinema.utils.enums.ReviewStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {


    @Autowired
    ReviewServiceInterface reviewService;

    @Autowired
    ClientServiceInterface clientService;



    @PostMapping("/api/current/create_review")
    public ResponseEntity<Object> create_review(Authentication authentication, CreateReviewDto createReviewDto){

        return new ResponseEntity<>("In progress..", HttpStatus.OK);


    }

    @GetMapping("/api/movie/reviews")
    public ResponseEntity<Object> getApprovedReviewsByMovie(@RequestParam Long movieId){

        List<ReviewDto> reviewsFound = ReviewUtils.sortReviewSet(reviewService.findByMovie_IdAndReviewStatus(movieId));



        if(reviewsFound.size() > 0){
            return new ResponseEntity<>(reviewsFound, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No reviews found", HttpStatus.CONFLICT);
        }





    }




    @GetMapping("/api/current/get_user_review_by_movie")
    public ResponseEntity<Object> getReviewByUserAndMovie(Authentication authentication, @RequestParam Long movieId){

        Client client = clientService.get_full_client(authentication);


        if(!reviewService.existsByClientAndMovie_id(client, movieId)){

            return new ResponseEntity<>("No reviews by user found", HttpStatus.CONFLICT);
        } else {


           return new ResponseEntity<>(reviewService.findByClientAndMovie_id(client, movieId), HttpStatus.OK);
        }

    }





}
