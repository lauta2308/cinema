package com.mindhub.cinema.dtos.param_dtos;

import org.springframework.web.bind.annotation.RequestParam;

public class AddPurchaseItemDto {

    private Integer productQuantity;


    private Long purchaseId;


    private Long productId;


    public AddPurchaseItemDto(Integer productQuantity, Long purchaseId, Long productId) {
        this.productQuantity = productQuantity;
        this.purchaseId = purchaseId;
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public Long getProductId() {
        return productId;
    }
}
