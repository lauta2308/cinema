package com.mindhub.cinema;

import com.mindhub.cinema.models.CinemaRoom;
import com.mindhub.cinema.models.Client;
import com.mindhub.cinema.models.RoomType;
import com.mindhub.cinema.models.Seat;
import com.mindhub.cinema.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}



	@Bean
	public CommandLineRunner initData(ActorRepository actorRepository, CinemaRoomRepository cinemaRoomRepository, ClientRepository clientRepository, MovieRepository movieRepository, ProductRepository productRepository, PurchaseItemRepository purchaseItemRepository, PurchaseRepository purchaseRepository, ReviewRepository reviewRepository, SeatRepository seatRepository, ShowRepository showRepository, TicketRepository ticketRepository) {
		return (args) -> {


			// save customers

			Client clientOne = clientRepository.save(new Client("Abdul", "Randall", "abdulrandall@gmail.com", "1234", LocalDate.of(1994, 07, 20)));

			Client clientTwo = clientRepository.save(new Client("Bobby", "Pearce", "bobby_pearce@gmail.com", "1234", LocalDate.of(1993, 07, 20)));


			Client clientThree = clientRepository.save(new Client("Nannie", "Espinoza", "nannie_espinoza@gmail.com", "1234", LocalDate.of(1994, 05, 20)));


			Client clientFour = clientRepository.save(new Client("Junior", "Fowler", "junior_fowler@gmail.com", "1234", LocalDate.of(1991, 07, 20)));

			Client clientFive = clientRepository.save(new Client("Roberta", "Lara", "roberta_lara@gmail.com", "1234", LocalDate.of(1985, 07, 20)));


			Client clientSix = clientRepository.save(new Client("Layla", "Steele", "layla_steele@gmail.com", "1234", LocalDate.of(2000, 07, 20)));

			Client clientSeven = clientRepository.save(new Client("Axel", "Casey", "axel_casey@gmail.com", "1234", LocalDate.of(2005, 03, 20)));


			Client clientEight = clientRepository.save(new Client("Ishaq", "Hawkins", "ishaq_hawkins@gmail.com", "1234", LocalDate.of(1997, 07, 20)));

			Client clientNine = clientRepository.save(new Client("Aston", "Cantu", "aston_cantu@gmail.com", "1234", LocalDate.of(1999, 04, 27)));

			Client clientTen = clientRepository.save(new Client("Kiana", "Slater", "kiana_slater@gmail.com", "1234", LocalDate.of(1958, 03, 20)));



			// save cinema rooms

			CinemaRoom cinemaRoomOne = cinemaRoomRepository.save(new CinemaRoom("SALA 1 2D", 100, RoomType.ROOM_2D));

			CinemaRoom cinemaRoomTwo = cinemaRoomRepository.save(new CinemaRoom("SALA 2 3D", 50, RoomType.ROOM_3D));

			CinemaRoom cinemaRoomThree = cinemaRoomRepository.save(new CinemaRoom("SALA 3 IMAX", 30, RoomType.ROOM_IMAX));


			// Seats for room 1

			for (int i = 0; i <= cinemaRoomOne.getCapacity() ; i++) {
				seatRepository.save(new Seat(i, cinemaRoomOne));

			}

			// Seats for room 2

			for (int i = 0; i <= cinemaRoomTwo.getCapacity() ; i++) {
				seatRepository.save(new Seat(i, cinemaRoomTwo));
			}


			// Seats for room 3

			for (int i = 0; i <= cinemaRoomThree.getCapacity() ; i++) {
				seatRepository.save(new Seat(i, cinemaRoomThree));
			}

		};

	}




}
