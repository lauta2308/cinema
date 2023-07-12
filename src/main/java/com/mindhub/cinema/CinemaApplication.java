package com.mindhub.cinema;

import com.mindhub.cinema.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}



	@Bean
	public CommandLineRunner initData(ActorRepository actorRepository, CinemaRoomRepository cinemaRoomRepository, ClientRepository clientRepository, MovieRepository movieRepository, ProductRepository productRepository, PurchaseItemRepository purchaseItemRepository, PurchaseRepository purchaseRepository, ReviewRepository reviewRepository, SeatRepository seatRepository, ShowRepository showRepository, TicketRepository ticketRepository) {
		return (args) -> {









		};

	}




}
