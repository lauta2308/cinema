package com.mindhub.cinema.dtos;

public class PurchaseItemDto {

    private long id;

    private Integer quantity;



    private ProductDto product;


    public PurchaseItemDto(com.mindhub.cinema.models.PurchaseItem purchaseItem) {
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
