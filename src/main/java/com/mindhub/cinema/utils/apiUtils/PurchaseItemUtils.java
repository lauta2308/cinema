package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.param_dtos.AddPurchaseItemDto;

import java.util.Set;

public class PurchaseItemUtils {


    public static boolean allPurchaseIdsAreEqual(Set<AddPurchaseItemDto> purchaseItems) {

        Long firstPurchaseId = null;
        for (AddPurchaseItemDto purchaseItem : purchaseItems) {
            if (firstPurchaseId == null) {
                firstPurchaseId = purchaseItem.getPurchaseId();
            } else if (!firstPurchaseId.equals(purchaseItem.getPurchaseId())) {

                return false;

            }
        }

        return true;
    }


    public static AddPurchaseItemDto getFirstPurchaseItem(Set<AddPurchaseItemDto> purchaseItem) {

        return purchaseItem.stream().findFirst().get();
    }


}
