package com.mindhub.cinema.repositories;

import com.mindhub.cinema.models.ProductCombo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductComboRepository extends JpaRepository<ProductCombo, Long> {
}