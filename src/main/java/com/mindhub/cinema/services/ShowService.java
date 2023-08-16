package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.models_dtos.ShowDto;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.repositories.ShowRepository;
import com.mindhub.cinema.services.servinterfaces.ShowServiceInterface;
import com.mindhub.cinema.utils.apiUtils.ShowUtils;
import com.mindhub.cinema.utils.enums.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
    public List<ShowDto> get_movie_shows(Long movieId) {



        return ShowUtils.showSetToDto(showRepository.findByMovie_IdAndStartTimeAfter(movieId, LocalDateTime.now()).stream().collect(Collectors.toSet()));

    }

    @Override
    public boolean existsById(Long showId) {
        return showRepository.existsById(showId);
    }

    @Override
    public List<ShowDto> get_2d_shows() {
        return ShowUtils.showSetToDto(showRepository.findByMovie_movieTypeAndStartTimeAfter(MovieType.MOVIE_2D, LocalDateTime.now()).stream().collect(Collectors.toSet()));
    }

    @Override
    public List<ShowDto> get_3d_shows() {

        return ShowUtils.showSetToDto(showRepository.findByMovie_movieTypeAndStartTimeAfter(MovieType.MOVIE_3D, LocalDateTime.now()).stream().collect(Collectors.toSet()));
    }

    @Override
    public List<ShowDto> get_imax_shows() {
        return ShowUtils.showSetToDto(showRepository.findByMovie_movieTypeAndStartTimeAfter(MovieType.MOVIE_IMAX, LocalDateTime.now()).stream().collect(Collectors.toSet()));
    }
}
