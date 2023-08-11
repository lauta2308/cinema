package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.models_dtos.ReviewDto;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Review;
import com.mindhub.cinema.utils.enums.ReviewStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface ReviewServiceInterface {
    boolean existsByClientAndMovie_id(Client client, Long movieId);

    ReviewDto findByClientAndMovie_id(Client client, Long movieId);

    Set<Review> findByMovie_IdAndReviewStatus(Long movieId);
}
