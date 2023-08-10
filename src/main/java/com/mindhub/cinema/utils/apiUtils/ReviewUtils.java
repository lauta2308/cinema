package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.models_dtos.PurchaseDto;
import com.mindhub.cinema.dtos.models_dtos.ReviewDto;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.models.Review;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReviewUtils {

    public static List<ReviewDto> sortReviewSet(Set<Review> reviews){

        List<Review> reviewsList = reviews.stream().sorted(Comparator.comparing(Review::getReviewDate).reversed()).collect(Collectors.toList());



        return reviewListToDto(reviewsList);


    }

    public static List<ReviewDto> reviewListToDto(List<Review> reviewsList){
        return reviewsList.stream().map(review -> reviewToDto(review)).collect(Collectors.toList());
    }

    public static ReviewDto reviewToDto(Review review){
        return new ReviewDto(review);
    }



}
