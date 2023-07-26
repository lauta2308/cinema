package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.models_dtos.MovieDto;
import com.mindhub.cinema.dtos.models_dtos.SeatDto;
import com.mindhub.cinema.models.Seat;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SeatUtils {


    public static List<SeatDto> seatSetToDto(List<Seat> seats) {

        List<SeatDto> seatsList = seats.stream().map(seat -> new SeatDto(seat)).collect(Collectors.toList());


        seatsList.sort(Comparator.comparing(SeatDto::getId));

        return seatsList;
    }


}
