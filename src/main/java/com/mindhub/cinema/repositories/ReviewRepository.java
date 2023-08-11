package com.mindhub.cinema.repositories;


import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.Review;
import com.mindhub.cinema.utils.enums.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByClientAndMovie_idAndReviewStatus(Client client, Long movieId, ReviewStatus reviewStatus);

    Review findByClientAndMovie_idAndReviewStatus(Client client, Long movieId, ReviewStatus reviewStatus);

    Set<Review> findByMovie_IdAndReviewStatus(Long movieId, ReviewStatus reviewStatus);
}
