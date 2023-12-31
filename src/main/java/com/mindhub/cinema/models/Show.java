package com.mindhub.cinema.models;


import com.mindhub.cinema.utils.apiUtils.DateUtils;
import com.mindhub.cinema.utils.enums.ShowStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double standardPrice;

    private Integer ticketsSold = 0;

    private Double occupationPercent = 0.0;

    private ShowStatus showStatus = ShowStatus.NOT_STARTED;

    @OneToMany(mappedBy="show", fetch=FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cinema_room_id")
    private CinemaRoom cinemaRoom;


    public Show() {
    }

    public Show(LocalDateTime startTime, Double standardPrice, Movie movie, CinemaRoom cinemaRoom) {
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getDuration());
        this.standardPrice = standardPrice;
        this.movie = movie;
        this.cinemaRoom = cinemaRoom;
    }

    public void addTicketsSold(Integer quantity){
        this.setTicketsSold(this.getTicketsSold() + quantity);
        this.updateOccupationPercent();
    }


    public void deductTicketsSold(Integer quantity){
        this.setTicketsSold(this.getTicketsSold() - quantity);
        this.updateOccupationPercent();
    }


    public void updateOccupationPercent(){
        this.setOccupationPercent(this.getTicketsSold().doubleValue() * 100 / this.getCinemaRoom().getCapacity());
    }


    public long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(Double standardPrice) {
        this.standardPrice = standardPrice;
    }

    public Integer getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(Integer ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public Double getOccupationPercent() {
        return occupationPercent;
    }

    public void setOccupationPercent(Double occupationPercent) {
        this.occupationPercent = occupationPercent;
    }

    public ShowStatus getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(ShowStatus showStatus) {
        this.showStatus = showStatus;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }
}
