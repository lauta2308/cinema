package com.mindhub.cinema.dtos.models_dtos;

import com.mindhub.cinema.dtos.models_dtos.CinemaRoomDto;
import com.mindhub.cinema.dtos.models_dtos.MovieDto;
import com.mindhub.cinema.models.Show;
import com.mindhub.cinema.utils.apiUtils.DateUtils;
import com.mindhub.cinema.utils.enums.ShowStatus;

import java.time.LocalDateTime;

public class ShowDto {

    private long id;

    private String startTime;

    private String endTime;

    private Double standardPrice;

    private Integer ticketsSold;

    private Double occupationPercent;

    private ShowStatus showStatus;




    private MovieDto movie;

    private CinemaRoomDto cinemaRoom;


    public ShowDto(Show show) {
        this.id = show.getId();
        this.startTime = DateUtils.dateTimeFormatter(show.getStartTime());
        this.endTime = DateUtils.timeFormatter(show.getEndTime());
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

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
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
