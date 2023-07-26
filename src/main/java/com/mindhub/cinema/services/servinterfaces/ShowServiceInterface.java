package com.mindhub.cinema.services.servinterfaces;

import com.mindhub.cinema.dtos.models_dtos.ShowDto;
import com.mindhub.cinema.models.Show;

import java.util.List;
import java.util.Set;

public interface ShowServiceInterface {
    Show getShow(Long showId);


    List<ShowDto> get_movie_shows(Long movieId);

    boolean existsById(Long showId);
}
