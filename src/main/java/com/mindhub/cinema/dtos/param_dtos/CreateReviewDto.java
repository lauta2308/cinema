package com.mindhub.cinema.dtos.param_dtos;

public class CreateReviewDto {

    private Integer stars;

    private String comment;

    private Long movieId;

    public CreateReviewDto(Integer stars, String comment, Long movieId) {
        this.stars = stars;
        this.comment = comment;
        this.movieId = movieId;
    }

    public Integer getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public Long getMovieId() {
        return movieId;
    }
}
