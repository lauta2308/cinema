package com.mindhub.cinema.dtos.param_dtos;

public class UpdateProductStockDto {

    private Long productId;

    private Integer productStock;

    public UpdateProductStockDto(Long productId, Integer productStock) {
        this.productId = productId;
        this.productStock = productStock;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getProductStock() {
        return productStock;
    }
}
