package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.param_dtos.AddPurchaseItemDto;

import java.util.List;

public class PurchaseItemUtils {




    public static AddPurchaseItemDto getFirstPurchaseItem(List<AddPurchaseItemDto> purchaseItem) {

        return purchaseItem.stream().findFirst().get();
    }


}
