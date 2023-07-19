package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.ShowDto;
import com.mindhub.cinema.models.Show;

import java.util.Set;

public interface ShowServiceInterface {
    Show getShow(Long showId);


    Set<ShowDto> get_movie_shows(Long movieId);

    boolean existsById(Long showId);
}
