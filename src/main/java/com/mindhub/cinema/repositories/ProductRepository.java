package com.mindhub.cinema.repositories;

import com.mindhub.cinema.dtos.models_dtos.ProductDto;
import com.mindhub.cinema.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    boolean existsByName(String name);

    Set<ProductDto> findByAvailable(boolean available);
}
