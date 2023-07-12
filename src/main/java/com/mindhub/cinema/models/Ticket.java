package com.mindhub.cinema.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Long seatId;

    private Integer seatPlace;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="purchase_id")
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="show_id")
    private Show show;


    public Ticket() {
    }

    public Ticket(Long seatId, Integer seatPlace, Purchase purchase, Show show) {
        this.seatId = seatId;
        this.seatPlace = seatPlace;
        this.purchase = purchase;
        this.show = show;
    }

    public long getId() {
        return id;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Integer getSeatPlace() {
        return seatPlace;
    }

    public void setSeatPlace(Integer seatPlace) {
        this.seatPlace = seatPlace;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
