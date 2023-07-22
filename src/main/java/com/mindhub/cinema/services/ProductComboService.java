package com.mindhub.cinema.services;


import com.mindhub.cinema.dtos.models_dtos.ProductComboDto;
import com.mindhub.cinema.dtos.models_dtos.ProductDto;
import com.mindhub.cinema.dtos.param_dtos.BuyProductComboDto;
import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.models.ProductCombo;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.repositories.ProductComboRepository;
import com.mindhub.cinema.repositories.ProductRepository;
import com.mindhub.cinema.services.servinterfaces.ProductComboServiceInterface;
import com.mindhub.cinema.services.servinterfaces.ProductServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import com.mindhub.cinema.utils.exceptions.InsufficientStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductComboService implements ProductComboServiceInterface {

    @Autowired
    ProductComboRepository productComboRepository;

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    PurchaseServiceInterface purchaseService;

    @Autowired
    ProductRepository productRepository;


    @Override
    public Set<ProductComboDto> findAll() {
        return productComboRepository.findAll().stream().map(productCombo -> new ProductComboDto(productCombo)).collect(Collectors.toSet());
    }

    @Override
    @Transactional // Asegura que se revierta la transacción para cualquier excepción
    public String verifyProductsStock(List<BuyProductComboDto> productCombosIds) {

        List<ProductComboDto> productCombos = new ArrayList<>();
        for (ProductComboDto productCombo : productCombos) {
            for (ProductDto productDto : productCombo.getProducts()) {
                Product product = productService.findById(productDto.getId());
                if (product.getStock() < 1) {
                    throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
                } else {
                    decreaseProductsStock(product);
                }
            }
        }

        return "All products verified and stocks decreased.";



    }




    @Transactional
    public void decreaseProductsStock(Product product) {

        if (product.getStock() < 1) {
            throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
        }
        product.setStock(product.getStock() - 1);
        productService.save(product); // Actualizar el stock en la base de datos
    }


    @Override
    @Transactional
    public String addProductCombosToPurchase(List<BuyProductComboDto> productComboIds, Purchase purchase) {
        Double totalPrice = purchase.getPurchase_price(); // Inicializar el precio total con el precio actual de la compra

        List<ProductComboDto> productComboDtoList = getProductComboDtos(productComboIds);

        for (ProductComboDto productComboDto : productComboDtoList) {
            ProductCombo productCombo = getProductComboById(productComboDto.getId());
            ProductCombo newProductCombo = createNewProductCombo(productCombo, purchase);
            productComboRepository.save(newProductCombo);


            totalPrice = totalPrice + newProductCombo.getComboFinalPrice();
        }

        purchaseService.replacePurchasePrice(purchase, totalPrice);


        return "ProductCombos added to purchase successfully!";
    }

    private ProductCombo getProductComboById(Long productComboId) {
        return productComboRepository.findById(productComboId)
                .orElseThrow(() -> new EntityNotFoundException("ProductCombo with id " + productComboId + " not found"));
    }

    private ProductCombo createNewProductCombo(ProductCombo originalProductCombo, Purchase purchase) {

        ProductCombo newProductCombo = productComboRepository.save(new ProductCombo());

        newProductCombo.setTemplateCombo(false);
        newProductCombo.setProductComboType(originalProductCombo.getProductComboType());
        newProductCombo.setComboDefaultPrice(originalProductCombo.getComboDefaultPrice());
        newProductCombo.setComboFinalPrice(originalProductCombo.getComboFinalPrice());
        newProductCombo.setProductComboType(originalProductCombo.getProductComboType());
        newProductCombo.setPurchase(purchase);


        List<Product> clonedProducts = cloneProducts(originalProductCombo.getProducts());
        newProductCombo.setProducts(clonedProducts);

        return newProductCombo;
    }

    private List<Product> cloneProducts(List<Product> originalProducts) {
        List<Product> clonedProducts = new ArrayList<>();
        for (Product product : originalProducts) {
            Product clonedProduct = productRepository.findById(product.getId()).orElse(null);
            if (clonedProduct != null) {
                clonedProducts.add(clonedProduct);
            }
        }
        return clonedProducts;
    }



    private List<ProductComboDto> getProductComboDtos(List<BuyProductComboDto> buyProductComboDtoList){

        List<ProductComboDto> productComboDtoList = new ArrayList<>();
        for (BuyProductComboDto buyProductComboto : buyProductComboDtoList){
           productComboDtoList.add(new ProductComboDto(productComboRepository.findById(buyProductComboto.getProductComboId()).get()));
        }

        return productComboDtoList;
    }





}