package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.models_dtos.ProductComboDto;

import java.util.Set;

public interface ProductComboServiceInterface {
    Set<ProductComboDto> findAll();
}
