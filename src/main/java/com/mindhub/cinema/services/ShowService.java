package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.models_dtos.ShowDto;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.models.Ticket;
import com.mindhub.cinema.repositories.ShowRepository;
import com.mindhub.cinema.services.servinterfaces.ShowServiceInterface;
import com.mindhub.cinema.utils.apiUtils.MovieUtils;
import com.mindhub.cinema.utils.apiUtils.ShowUtils;
import com.mindhub.cinema.utils.apiUtils.TicketUtils;
import com.mindhub.cinema.utils.enums.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public List<ShowDto> get_showTimes(MovieType movieType) {
        return ShowUtils.showSetToDto(showRepository.findByMovie_movieTypeAndStartTimeAfter(movieType, LocalDateTime.now()).stream().collect(Collectors.toSet()));
    }

    @Override
    public List<Show> findShowsByStartTime(Integer hours) {
        return showRepository.findByStartTimeBetween(LocalDateTime.now().minusHours(hours), LocalDateTime.now());
    }

    @Override
    public Object get_shows() {

        Sort sort = Sort.by(Sort.Direction.ASC, "startTime");

       return ShowUtils.showsToDto( showRepository.findByStartTimeAfter( LocalDateTime.now(), sort));

    }

    @Override
    public void updateShowTicketsSold(List<Ticket> purchaseTickets) {

        Show show = showRepository.findById(purchaseTickets.stream().findFirst().get().getShow().getId()).get();

        show.addTicketsSold(purchaseTickets.size());

        showRepository.save(show);
    }
}
