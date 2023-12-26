package com.mindhub.cinema.services;

import com.mindhub.cinema.dtos.models_dtos.CinemaRoomDto;
import com.mindhub.cinema.dtos.param_dtos.CreateRoomDto;
import com.mindhub.cinema.dtos.param_dtos.EditRoomDto;
import com.mindhub.cinema.models.CinemaRoom;
import com.mindhub.cinema.repositories.CinemaRoomRepository;
import com.mindhub.cinema.services.servinterfaces.CinemaRoomServiceInterface;
import com.mindhub.cinema.services.servinterfaces.SeatServiceInterface;
import com.mindhub.cinema.utils.apiUtils.CinemaRoomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    @Override
    public List<CinemaRoomDto> getRooms() {
        return CinemaRoomUtils.cinemaRoomsToDto(cinemaRoomRepository.findAll());
    }

    @Override
    public boolean existById(Long roomId) {
        return cinemaRoomRepository.existsById(roomId);
    }

    @Override
    public void editRoom(EditRoomDto editRoomDto) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(editRoomDto.getRoomId()).get();

        cinemaRoom.setRoomName(editRoomDto.getRoomName());
        cinemaRoom.setRoomType(editRoomDto.getRoomType());
        cinemaRoom.setRoomStatus(editRoomDto.getRoomStatus());
        cinemaRoom.setCapacity(editRoomDto.getCapacity());

        cinemaRoomRepository.save(cinemaRoom);
    }
}
