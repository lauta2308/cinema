package com.mindhub.cinema.dtos.models_dtos;

import com.mindhub.cinema.dtos.models_dtos.ProductDto;
import com.mindhub.cinema.models.PurchaseItem;

public class PurchaseItemDto {

    private long id;

    private Integer quantity;

    private ProductDto product;


    public PurchaseItemDto(PurchaseItem purchaseItem) {
        this.id = purchaseItem.getId();
        this.quantity = purchaseItem.getQuantity();
        this.product = new ProductDto(purchaseItem.getProduct());
    }

    public long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }


    public ProductDto getProduct() {
        return product;
    }
}
