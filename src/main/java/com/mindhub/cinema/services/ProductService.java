package com.mindhub.cinema.services;

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

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity<String> add_product(Authentication authentication, String productName, Double productPrice, Integer stock, ProductType productType, Integer net_content) {


        if(authentication == null){
            return new ResponseEntity<>("Login first", HttpStatus.FORBIDDEN);
        }

        if(productName.isBlank() || productPrice.toString().isBlank() || stock.toString().isBlank() || productType.toString().isBlank() || net_content.toString().isBlank()){

            return new ResponseEntity<>("Empty fields", HttpStatus.BAD_REQUEST);
        }



        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ADMIN"));


        if(!isAdmin){
            return new ResponseEntity<>("Not an admin", HttpStatus.CONFLICT);
        } else {
            Product product = productRepository.save(new Product(productName, productPrice, stock, productType, net_content));


            if(product != null){
                return new ResponseEntity<>("Product added", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("There was a problem saving the product", HttpStatus.CONFLICT);
            }

        }




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
}