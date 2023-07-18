package com.mindhub.cinema.controllers;


import com.mindhub.cinema.models.CinemaRoom;
import com.mindhub.cinema.services.servinterfaces.CinemaRoomServiceInterface;
import com.mindhub.cinema.utils.enums.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaRoomController {

    @Autowired
    CinemaRoomServiceInterface cinemaRoomService;

    @PostMapping("/api/admin/cinema_room")
    ResponseEntity<String> create_cinema_room(@RequestParam String roomName, @RequestParam Integer capacity, @RequestParam RoomType roomType){

        if(roomName.isBlank()){
            return new ResponseEntity<>("Insert room name", HttpStatus.BAD_REQUEST);
        }

        if(capacity <= 0){
            return new ResponseEntity<>("Capacity should be more than 0", HttpStatus.BAD_REQUEST);
        }

        if(roomType.toString().isBlank()){
            return new ResponseEntity<>("Insert room type", HttpStatus.BAD_REQUEST);
        }


        if(cinemaRoomService.roomNameDuplicated(roomName)){
           return new ResponseEntity<>("Room name duplicated", HttpStatus.CONFLICT);
        }


        return cinemaRoomService.create_cinema_room(roomName, capacity, roomType);

    }





}
