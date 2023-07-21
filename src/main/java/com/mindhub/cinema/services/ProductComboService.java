package com.mindhub.cinema.services;


import com.mindhub.cinema.dtos.ProductComboDto;
import com.mindhub.cinema.repositories.ProductComboRepository;
import com.mindhub.cinema.services.servinterfaces.ProductComboServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductComboService implements ProductComboServiceInterface {

    @Autowired
    ProductComboRepository productComboRepository;
    @Override
    public Set<ProductComboDto> findAll() {
        return productComboRepository.findAll().stream().map(productCombo -> new ProductComboDto(productCombo)).collect(Collectors.toSet());
    }
}
