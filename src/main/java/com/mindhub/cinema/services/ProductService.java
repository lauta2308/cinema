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


        productRepository.save(new Product(createProductDto.getProductName(), createProductDto.getProductPrice(), createProductDto.getStock(), createProductDto.getProductType(), createProductDto.getNet_content()));




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
    public String allProductsExist(Set<AddPurchaseItemDto> purchaseItems) {

        StringBuilder message = new StringBuilder();
        boolean allProductsExist = true;

        for (AddPurchaseItemDto purchaseItem : purchaseItems) {
            Long productId = purchaseItem.getProductId();
            int productQuantity = purchaseItem.getProductQuantity();

            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isEmpty()) {
                allProductsExist = false;
                message.append("the product with the id: ").append(productId).append(" does not exist.\n");
            } else {
                Product product = optionalProduct.get();
                int productStock = product.getStock();
                if (productQuantity > productStock) {
                    allProductsExist = false;
                    message.append("Stock not available for product: ").append(product.getName()).append("\n");
                }
            }
        }

        if (allProductsExist) {
            return "All the products are valid, stock available";
        } else {
            return message.toString();
        }
    }

}
