package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.param_dtos.AddPurchaseItemDto;
import com.mindhub.cinema.models.Product;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.models.PurchaseItem;
import com.mindhub.cinema.repositories.ProductRepository;
import com.mindhub.cinema.repositories.PurchaseItemRepository;
import com.mindhub.cinema.services.servinterfaces.PurchaseItemServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseItemService implements PurchaseItemServiceInterface {


    @Autowired
    PurchaseItemRepository purchaseItemRepository;

    @Autowired
    ProductRepository productRepository;



    @Override
    public void save_purchase_items(List<AddPurchaseItemDto> purchaseItems, Purchase purchase) {

        for (AddPurchaseItemDto purchaseItemDto : purchaseItems) {
            Long productId = purchaseItemDto.getProductId();
            int productQuantity = purchaseItemDto.getProductQuantity();

            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                PurchaseItem purchaseItem = new PurchaseItem(productQuantity, purchase, product);
                purchaseItemRepository.save(purchaseItem);
                purchaseItem.addPriceToPurchase();
                productRepository.save(product);
            }

        }


    }

    public List<PurchaseItem> getPurchaseItems(Long purchaseId){
        return purchaseItemRepository.findByPurchaseId(purchaseId);
    }
}