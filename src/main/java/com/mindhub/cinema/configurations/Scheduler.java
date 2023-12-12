package com.mindhub.cinema.configurations;


import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.services.servinterfaces.MovieServiceInterface;
import com.mindhub.cinema.services.servinterfaces.PurchaseServiceInterface;
import com.mindhub.cinema.services.servinterfaces.ShowServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@EnableScheduling
public class Scheduler {


    @Autowired
   PurchaseServiceInterface purchaseService;

    @Autowired
    MovieServiceInterface movieService;

    @Autowired
    ShowServiceInterface showService;


    // this is executed each 15 minutes to cancel incomplete purchases.
    @Scheduled(fixedDelay = 900000)
    public void schedulePurchaseCancellation() {
        purchaseService.checkAndCancelExpiredPurchases();
    }


    // this is executed each 4 hours to update times played for a movie.
    @Scheduled(fixedDelay = 14400000)
    public void updateMovieTimesPlayed() {


        System.out.println("Checking shows played...");

        // find shows started in the past 4 hours
        List<Show> shows = showService.findShowsByStartTime(4);

        System.out.println(shows.size());

        if(shows.size() > 0){
            System.out.println("Updating...");
            // Add one time played for each movie of each show
            movieService.updateMovieTimesPlayed(shows);
        }





    }
}
