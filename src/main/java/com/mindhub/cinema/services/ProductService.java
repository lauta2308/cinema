package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.param_dtos.AddPurchaseItemDto;
import com.mindhub.cinema.dtos.param_dtos.CreateProductDto;
import com.mindhub.cinema.dtos.models_dtos.ProductDto;
import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.repositories.ProductRepository;
import com.mindhub.cinema.services.servinterfaces.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void add_product(CreateProductDto createProductDto) {


        productRepository.save(new Product(createProductDto.getProductName(), createProductDto.getProductPrice(), createProductDto.getProductType(), createProductDto.getNet_content()));




    }

    @Override
    public Set<ProductDto> getAllProductsAvailable() {
        return productRepository.findByAvailable(true);
    }

    @Override
    public Set<ProductDto> getAllProducts() {
        return  productRepository.findAll().stream().map(product -> new ProductDto(product)).collect(Collectors.toSet());
    }






    @Override
    public boolean existsByName(String productName) {
        return productRepository.existsByName(productName);
    }

    @Override
    public String allProductsExist(List<AddPurchaseItemDto> purchaseItems) {

        StringBuilder message = new StringBuilder();
        boolean allProductsExist = true;

        for (AddPurchaseItemDto purchaseItem : purchaseItems) {
            Long productId = purchaseItem.getProductId();
            int productQuantity = purchaseItem.getProductQuantity();

            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isEmpty()) {
                allProductsExist = false;
                message.append("the product with the id: ").append(productId).append(" does not exist.\n");
            }
            }


        if (allProductsExist) {
            return "All the products are valid, stock available";
        } else {
            return message.toString();
        }
    }


    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public boolean existsById(Long productId) {
        return productRepository.existsById(productId);
    }

    @Override
    public void changeAvailability(Long productId) {
        Product product = productRepository.findById(productId).get();

        if(product.getAvailable() == true){
            product.setAvailable(false);
        } else {
            product.setAvailable(true);
        }

        productRepository.save(product);
    }


}
