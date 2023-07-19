package com.mindhub.cinema.dtos;

import com.mindhub.cinema.models.Review;
import com.mindhub.cinema.utils.enums.ReviewStatus;

public class ReviewDto {

    private long id;

    private Integer stars;

    private String comment;

    private MovieDto movie;

    private ReviewStatus reviewStatus;


    public ReviewDto(Review review) {
        this.id = review.getId();
        this.stars = review.getStars();
        this.comment = review.getComment();
        this.movie = new MovieDto(review.getMovie());
        this.reviewStatus =review.getReviewStatus();
    }

    public long getId() {
        return id;
    }

    public Integer getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }
}
