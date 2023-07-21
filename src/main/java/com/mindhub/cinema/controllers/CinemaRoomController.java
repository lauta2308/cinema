package com.mindhub.cinema.controllers;


import com.mindhub.cinema.dtos.CreateRoomDto;
import com.mindhub.cinema.services.servinterfaces.CinemaRoomServiceInterface;
import com.mindhub.cinema.utils.apiUtils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaRoomController {

    @Autowired
    CinemaRoomServiceInterface cinemaRoomService;

    @PostMapping("/api/admin/cinema_room")
    ResponseEntity<String> create_cinema_room(Authentication authentication, @RequestBody CreateRoomDto createRoomDto){


      if(authentication == null){
            return new ResponseEntity<>("Login first", HttpStatus.FORBIDDEN);
        }

        if(ValidationUtils.checkUserRole(authentication) != "ADMIN"){
            return new ResponseEntity<>("Not an admin", HttpStatus.CONFLICT);
        }


        if(createRoomDto.getRoomName().isBlank()){
            return new ResponseEntity<>("Insert room name", HttpStatus.BAD_REQUEST);
        }

        if(createRoomDto.getCapacity() <= 0){
            return new ResponseEntity<>("Capacity should be more than 0", HttpStatus.BAD_REQUEST);
        }

        if(createRoomDto.getRoomType().toString().isBlank()){
            return new ResponseEntity<>("Insert room type", HttpStatus.BAD_REQUEST);
        }


        if(cinemaRoomService.roomNameDuplicated(createRoomDto.getRoomName())){
           return new ResponseEntity<>("Room name duplicated", HttpStatus.CONFLICT);
        }


       cinemaRoomService.create_cinema_room(createRoomDto);

        return new ResponseEntity<>("Room made, seats added", HttpStatus.CREATED);

    }





}
