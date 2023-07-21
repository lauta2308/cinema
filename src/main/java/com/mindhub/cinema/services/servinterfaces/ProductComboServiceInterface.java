package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.ProductComboDto;

import java.util.Set;

public interface ProductComboServiceInterface {
    Set<ProductComboDto> findAll();
}
