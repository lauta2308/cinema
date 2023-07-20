package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.CreateProductDto;
import com.mindhub.cinema.dtos.ProductDto;
import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.repositories.ProductRepository;
import com.mindhub.cinema.services.servinterfaces.ProductServiceInterface;
import com.mindhub.cinema.utils.enums.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void add_product(CreateProductDto createProductDto) {


        productRepository.save(new Product(createProductDto.getProductName(), createProductDto.getProductPrice(), createProductDto.getStock(), createProductDto.getProductType(), createProductDto.getNet_content()));




    }

    @Override
    public Set<ProductDto> getAllProducts() {
        return  productRepository.findAll().stream().map(product -> new ProductDto(product)).collect(Collectors.toSet());
    }

    @Override
    public Product findProductByid(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public Boolean existById(Long productId) {
        return productRepository.existsById(productId);
    }

    @Override
    public Set<List<ProductDto>> getCombos() {
        List<ProductDto> combo1 = new ArrayList<>();

        Product cocaMedia = productRepository.findByName("Coca-cola vaso mediano");

        Product pochocloMedio = productRepository.findByName("Balde pochoclo mediano");

        combo1.add(new ProductDto(cocaMedia));
        combo1.add(new ProductDto(pochocloMedio));


        List<ProductDto> comboFamilia = new ArrayList<>();

        comboFamilia.add(new ProductDto(cocaMedia));
        comboFamilia.add(new ProductDto(cocaMedia));
        comboFamilia.add(new ProductDto(cocaMedia));
        comboFamilia.add(new ProductDto(cocaMedia));
        comboFamilia.add(new ProductDto(pochocloMedio));
        comboFamilia.add(new ProductDto(pochocloMedio));

        Set productos = new HashSet<>();

        productos.add(combo1);
        productos.add(comboFamilia);


        Product pochocloGrande = productRepository.findByName("Balde pochoclo grande");
        Product cocaGrande = productRepository.findByName("Coca-cola vaso grande");


        List<ProductDto> comboMega = new ArrayList<>();

        comboMega.add(new ProductDto(pochocloGrande));
        comboMega.add(new ProductDto(cocaGrande));
        comboMega.add(new ProductDto(cocaGrande));

        productos.add(comboMega);

        return  productos;

    }

    @Override
    public boolean existsByName(String productName) {
        return productRepository.existsByName(productName);
    }
}
