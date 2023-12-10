package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.models_dtos.PurchaseItemDto;
import com.mindhub.cinema.dtos.param_dtos.AddPurchaseItemDto;
import com.mindhub.cinema.models.PurchaseItem;

import java.util.List;
import java.util.stream.Collectors;

public class PurchaseItemUtils {




    public static AddPurchaseItemDto getFirstPurchaseItem(List<AddPurchaseItemDto> purchaseItem) {

        return purchaseItem.stream().findFirst().get();
    }

    public static List<PurchaseItemDto> convertPurchaseItem(List<PurchaseItem> purchaseItems){
        return purchaseItems.stream().map(purchaseItem -> new PurchaseItemDto(purchaseItem)).collect(Collectors.toList());
    }


}
