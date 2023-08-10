package com.mindhub.cinema.dtos.models_dtos;

import com.mindhub.cinema.dtos.models_dtos.MovieDto;
import com.mindhub.cinema.models.Review;
import com.mindhub.cinema.utils.apiUtils.DateUtils;
import com.mindhub.cinema.utils.enums.ReviewStatus;

public class ReviewDto {

    private long id;

    private Integer stars;

    private String comment;



    private ReviewStatus reviewStatus;

    private String reviewDate;


    public ReviewDto(Review review) {
        this.id = review.getId();
        this.stars = review.getStars();
        this.comment = review.getComment();

        this.reviewStatus =review.getReviewStatus();
        this.reviewDate = DateUtils.dateTimeFormatter(review.getReviewDate());
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


    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public String getReviewDate() {
        return reviewDate;
    }
}
