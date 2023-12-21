package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.models_dtos.ShowDto;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.models.Ticket;
import com.mindhub.cinema.utils.enums.MovieType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ShowServiceInterface {
    Show getShow(Long showId);


    List<ShowDto> get_movie_shows(Long movieId);

    boolean existsById(Long showId);


    List<ShowDto> get_showTimes(MovieType movieType);

    List<Show> findShowsByStartTime(Integer hours);

    Object get_shows();

    void updateShowTicketsSold(List<Ticket> purchaseTickets);
}
