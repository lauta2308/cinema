package com.mindhub.cinema.services;


import com.mindhub.cinema.dtos.models_dtos.ProductComboDto;
import com.mindhub.cinema.dtos.models_dtos.ProductDto;
import com.mindhub.cinema.dtos.param_dtos.CreateProductComboDto;
import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.models.ProductCombo;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.repositories.ProductComboRepository;
import com.mindhub.cinema.repositories.ProductRepository;
import com.mindhub.cinema.services.servinterfaces.ProductComboServiceInterface;
import com.mindhub.cinema.services.servinterfaces.ProductServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import com.mindhub.cinema.utils.apiUtils.ProductComboUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
    public List<ProductComboDto> getTemplateCombos() {
         return ProductComboUtils.productComboToDto(productComboRepository.findByIsTemplateCombo(true));
    }




    @Override
    @Transactional
    public String addProductCombosToPurchase(List<ProductComboDto> productComboDtoList, Purchase purchase) {
        Double totalPrice = purchase.getPurchase_price(); // Inicializar el precio total con el precio actual de la compra



        for (ProductComboDto productComboDto : productComboDtoList) {
            ProductCombo productCombo = getProductComboById(productComboDto.getId());
            ProductCombo newProductCombo = createNewProductCombo(productCombo, purchase);
            productComboRepository.save(newProductCombo);


            totalPrice = totalPrice + newProductCombo.getPrice();
        }

        purchaseService.replacePurchasePrice(purchase, totalPrice);


        return "ProductCombos added to purchase successfully!";
    }

    @Override
    public void createProductCombo(CreateProductComboDto createProductComboDto) {

        List<Product> products = productService.findProductsList(createProductComboDto.getProducts());

        productComboRepository.save(new ProductCombo(createProductComboDto.getComboName(), createProductComboDto.getComboPrice(), createProductComboDto.isTemplateCombo(), products));


    }

    private ProductCombo getProductComboById(Long productComboId) {
        return productComboRepository.findById(productComboId)
                .orElseThrow(() -> new EntityNotFoundException("ProductCombo with id " + productComboId + " not found"));
    }

    private ProductCombo createNewProductCombo(ProductCombo originalProductCombo, Purchase purchase) {

        ProductCombo newProductCombo = productComboRepository.save(new ProductCombo());

        newProductCombo.setTemplateCombo(false);
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








}