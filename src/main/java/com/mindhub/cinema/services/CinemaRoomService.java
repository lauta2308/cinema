package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.CreateRoomDto;
import com.mindhub.cinema.models.CinemaRoom;
import com.mindhub.cinema.repositories.CinemaRoomRepository;
import com.mindhub.cinema.services.servinterfaces.CinemaRoomServiceInterface;
import com.mindhub.cinema.services.servinterfaces.SeatServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CinemaRoomService implements CinemaRoomServiceInterface {

    @Autowired
    CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    SeatServiceInterface seatService;

    @Override
    @Transactional
    public void create_cinema_room(CreateRoomDto createRoomDto) {


            seatService.addSeats(createRoomDto.getCapacity(),  cinemaRoomRepository.save(new CinemaRoom(createRoomDto.getRoomName(), createRoomDto.getCapacity(), createRoomDto.getRoomType())));

    }

    public boolean roomNameDuplicated(String roomName){
        return cinemaRoomRepository.existsByRoomName(roomName);
    }



    public CinemaRoom findByShow_Id(Long showId){
        return cinemaRoomRepository.findByShows_Id(showId);
    }
}
