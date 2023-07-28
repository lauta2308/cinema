package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.models_dtos.MovieDto;
import com.mindhub.cinema.dtos.models_dtos.ProductComboDto;
import com.mindhub.cinema.models.Movie;
import com.mindhub.cinema.models.ProductCombo;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductComboUtils {


    public static List<ProductComboDto> productComboToDto(Set<ProductCombo> productCombos) {

        List<ProductComboDto> comboList = productCombos.stream().map(productCombo -> new ProductComboDto(productCombo)).collect(Collectors.toList());


        comboList.sort(Comparator.comparing(ProductComboDto::getId));

        return comboList;
    }



}
