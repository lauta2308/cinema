package com.mindhub.cinema.controllers;


import com.mindhub.cinema.services.servinterfaces.CinemaRoomServiceInterface;
import com.mindhub.cinema.utils.enums.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
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

        return cinemaRoomService.create_cinema_room(roomName, capacity, roomType);

    }





}
