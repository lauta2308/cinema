package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.ShowDto;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.repositories.ShowRepository;
import com.mindhub.cinema.services.servinterfaces.ShowServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShowService implements ShowServiceInterface {
    @Autowired
    ShowRepository showRepository;
    @Override
    public Show getShow(Long showId) {

        return showRepository.findById(showId).get();


    }

    @Override
    public Set<ShowDto> get_movie_shows(Long movieId) {

        return showRepository.findByMovie_IdAndStartTimeAfter(movieId, LocalDateTime.now()).stream().map(show -> new ShowDto(show)).collect(Collectors.toSet());


    }

    @Override
    public boolean existsById(Long showId) {
        return showRepository.existsById(showId);
    }
}
