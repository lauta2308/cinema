package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.models_dtos.PurchaseDto;
import com.mindhub.cinema.models.Purchase;

public class PurchaseUtils {


    public static PurchaseDto purchaseToDto(Purchase purchase){
        return new PurchaseDto(purchase);
    }
}
