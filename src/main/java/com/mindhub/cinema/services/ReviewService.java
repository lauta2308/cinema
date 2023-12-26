package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.models_dtos.ReviewDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Review;
import com.mindhub.cinema.repositories.ReviewRepository;
import com.mindhub.cinema.services.servinterfaces.ReviewServiceInterface;
import com.mindhub.cinema.utils.apiUtils.ReviewUtils;
import com.mindhub.cinema.utils.enums.ReviewStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReviewService implements ReviewServiceInterface {

    @Autowired
    ReviewRepository reviewRepository;



    @Override
    public boolean existsByClientAndMovie_id(Client client, Long movieId) {
        return reviewRepository.existsByClientAndMovie_idAndReviewStatus(client, movieId, ReviewStatus.APPROVED);
    }

    @Override
    public ReviewDto findByClientAndMovie_id(Client client, Long movieId) {



        return ReviewUtils.reviewToDto(reviewRepository.findByClientAndMovie_idAndReviewStatus(client,movieId, ReviewStatus.APPROVED));
    }

    public Set<Review> findByMovie_IdAndReviewStatus(Long movieId) {

        return reviewRepository.findByMovie_IdAndReviewStatus(movieId, ReviewStatus.APPROVED);
    }

    public List<ReviewDto> get_reviews(){
        return reviewRepository.findAll().stream().map(review -> new ReviewDto(review)).collect(Collectors.toList());
    }

    @Override
    public void change_review_status(ReviewDto reviewDto) {
        Review review = reviewRepository.findById(reviewDto.getId()).get();

        System.out.println(review.getReviewStatus());
        review.setReviewStatus(reviewDto.getReviewStatus());

        System.out.println(reviewDto.getReviewStatus());

        review.getReviewStatus();

        reviewRepository.save(review);
    }
}
