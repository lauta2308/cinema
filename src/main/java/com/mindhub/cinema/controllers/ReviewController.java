package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.param_dtos.CreateReviewDto;
import com.mindhub.cinema.services.servinterfaces.ReviewServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {


    @Autowired
    ReviewServiceInterface reviewService;

    @PostMapping
    public ResponseEntity<Object> create_review(Authentication authentication, CreateReviewDto createReviewDto){

        return new ResponseEntity<>("In progress..", HttpStatus.OK);


    }





}
