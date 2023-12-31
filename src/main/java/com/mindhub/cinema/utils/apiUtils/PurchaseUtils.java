package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.models_dtos.ProductDto;
import com.mindhub.cinema.dtos.models_dtos.PurchaseDto;
import com.mindhub.cinema.dtos.models_dtos.PurchaseItemDto;
import com.mindhub.cinema.models.Purchase;
import com.mindhub.cinema.models.PurchaseItem;
import com.mindhub.cinema.repositories.PurchaseItemRepository;
import com.mindhub.cinema.utils.enums.PurchaseStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PurchaseUtils {



    public static List<PurchaseDto> filterAndSortCompletedPurchases(Set<Purchase> purchases){

        return sortPurchaseSet(purchases.stream().filter(purchase -> purchase.getPurchaseStatus() == PurchaseStatus.COMPLETED).collect(Collectors.toSet()));


    }

    public static List<PurchaseDto> filterAndSortUsedPurchases(Set<Purchase> purchases){

        return sortPurchaseSet(purchases.stream().filter(purchase -> purchase.getPurchaseStatus() == PurchaseStatus.USED).collect(Collectors.toSet()));


    }

    public static List<PurchaseDto> sortPurchaseSet(Set<Purchase> purchases) {

       List<Purchase> purchasesList = purchases.stream().sorted(Comparator.comparing(Purchase::getCreatedAt)).collect(Collectors.toList());



       return purchaseListToDto(purchasesList);


    }

    public static List<PurchaseDto> purchaseListToDto(List<Purchase> purchasesList){
        return purchasesList.stream().map(purchase -> purchaseToDto(purchase)).collect(Collectors.toList());
    }

    public static PurchaseDto purchaseToDto(Purchase purchase){
        return new PurchaseDto(purchase);
    }


    public static Long getMovieId(Purchase purchase) {


      return purchase.getTickets().stream().findFirst().get().getShow().getMovie().getId();


    }

    public static Long getMovieId(PurchaseDto purchasedto) {


        return purchasedto.getTickets().stream().findFirst().get().getShow().getMovie().getId();


    }
}
