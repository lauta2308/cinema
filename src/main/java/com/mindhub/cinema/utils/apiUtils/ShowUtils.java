package com.mindhub.cinema.utils.apiUtils;

import com.mindhub.cinema.dtos.models_dtos.MovieDto;
import com.mindhub.cinema.dtos.models_dtos.ShowDto;
import com.mindhub.cinema.models.Movie;
import com.mindhub.cinema.models.Show;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShowUtils {

    public static List<ShowDto> showSetToDto(Set<Show> shows) {

        List<ShowDto> showListDto = shows.stream().map(show -> new ShowDto(show)).collect(Collectors.toList());


        showListDto.sort(Comparator.comparing(ShowDto::getStartTime));

        return showListDto;
    }

    public static Object showsToDto(List<Show> shows) {

        return shows.stream().map(show -> new ShowDto(show)).collect(Collectors.toList());
    }
}
