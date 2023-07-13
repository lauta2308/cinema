package com.mindhub.cinema.dtos;

import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.utils.enums.ShowStatus;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class ShowDto {

    private long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double standardPrice;

    private Integer ticketsSold;

    private Double occupationPercent;

    private ShowStatus showStatus;




    private MovieDto movie;

    private CinemaRoomDto cinemaRoom;


    public ShowDto(Show show) {
        this.id = show.getId();
        this.startTime = show.getStartTime();
        this.endTime = show.getEndTime();
        this.standardPrice = show.getStandardPrice();
        this.ticketsSold = show.getTicketsSold();
        this.occupationPercent = show.getOccupationPercent();
        this.showStatus = show.getShowStatus();
        this.movie = new MovieDto(show.getMovie());
        this.cinemaRoom = new CinemaRoomDto(show.getCinemaRoom());
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Double getStandardPrice() {
        return standardPrice;
    }

    public Integer getTicketsSold() {
        return ticketsSold;
    }

    public Double getOccupationPercent() {
        return occupationPercent;
    }

    public ShowStatus getShowStatus() {
        return showStatus;
    }



    public MovieDto getMovie() {
        return movie;
    }

    public CinemaRoomDto getCinemaRoom() {
        return cinemaRoom;
    }
}
