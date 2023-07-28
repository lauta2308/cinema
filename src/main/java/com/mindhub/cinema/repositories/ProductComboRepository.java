package com.mindhub.cinema.repositories;

import com.mindhub.cinema.dtos.models_dtos.ProductComboDto;
import com.mindhub.cinema.models.ProductCombo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface ProductComboRepository extends JpaRepository<ProductCombo, Long> {
    Set<ProductCombo> findByIsTemplateCombo(boolean b);
}
