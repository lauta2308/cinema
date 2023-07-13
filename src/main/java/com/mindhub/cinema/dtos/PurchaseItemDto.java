package com.mindhub.cinema.dtos;

public class PurchaseItemDto {

    private long id;

    private Integer quantity;


    private PurchaseDto purchase;

    private ProductDto product;


    public PurchaseItemDto(com.mindhub.cinema.models.PurchaseItem purchaseItem) {
        this.id = purchaseItem.getId();
        this.quantity = purchaseItem.getQuantity();
        this.purchase = new PurchaseDto(purchaseItem.getPurchase());
        this.product = new ProductDto(purchaseItem.getProduct());
    }

    public long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public PurchaseDto getPurchase() {
        return purchase;
    }

    public ProductDto getProduct() {
        return product;
    }
}
