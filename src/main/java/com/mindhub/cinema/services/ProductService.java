package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.AddPurchaseItemDto;
import com.mindhub.cinema.dtos.CreateProductDto;
import com.mindhub.cinema.dtos.ProductDto;
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
