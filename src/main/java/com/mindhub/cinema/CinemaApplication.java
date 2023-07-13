package com.mindhub.cinema;

import com.mindhub.cinema.models.*;
import com.mindhub.cinema.repositories.*;
import com.mindhub.cinema.utils.enums.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}



	@Bean
	public CommandLineRunner initData(ActorRepository actorRepository, CinemaRoomRepository cinemaRoomRepository, ClientRepository clientRepository, DirectorRepository directorRepository, MovieRepository movieRepository, ProductRepository productRepository, PurchaseItemRepository purchaseItemRepository, PurchaseRepository purchaseRepository, ReviewRepository reviewRepository, SeatRepository seatRepository, ShowRepository showRepository, TicketRepository ticketRepository) {
		return (args) -> {


			// save customers

			Client clientOne = clientRepository.save(new Client("Abdul", "Randall", "abdulrandall@gmail.com", "1234", LocalDate.of(1994, 07, 20)));

			/*
			Client clientTwo = clientRepository.save(new Client("Bobby", "Pearce", "bobby_pearce@gmail.com", "1234", LocalDate.of(1993, 07, 20)));


			Client clientThree = clientRepository.save(new Client("Nannie", "Espinoza", "nannie_espinoza@gmail.com", "1234", LocalDate.of(1994, 05, 20)));


			Client clientFour = clientRepository.save(new Client("Junior", "Fowler", "junior_fowler@gmail.com", "1234", LocalDate.of(1991, 07, 20)));

			Client clientFive = clientRepository.save(new Client("Roberta", "Lara", "roberta_lara@gmail.com", "1234", LocalDate.of(1985, 07, 20)));


			Client clientSix = clientRepository.save(new Client("Layla", "Steele", "layla_steele@gmail.com", "1234", LocalDate.of(2000, 07, 20)));

			Client clientSeven = clientRepository.save(new Client("Axel", "Casey", "axel_casey@gmail.com", "1234", LocalDate.of(2005, 03, 20)));


			Client clientEight = clientRepository.save(new Client("Ishaq", "Hawkins", "ishaq_hawkins@gmail.com", "1234", LocalDate.of(1997, 07, 20)));

			Client clientNine = clientRepository.save(new Client("Aston", "Cantu", "aston_cantu@gmail.com", "1234", LocalDate.of(1999, 04, 27)));

			Client clientTen = clientRepository.save(new Client("Kiana", "Slater", "kiana_slater@gmail.com", "1234", LocalDate.of(1958, 03, 20)));
*/


			// save cinema rooms

			CinemaRoom cinemaRoomOne = cinemaRoomRepository.save(new CinemaRoom("SALA 1 2D", 100, RoomType.ROOM_2D));

			CinemaRoom cinemaRoomTwo = cinemaRoomRepository.save(new CinemaRoom("SALA 2 3D", 50, RoomType.ROOM_3D));

			CinemaRoom cinemaRoomThree = cinemaRoomRepository.save(new CinemaRoom("SALA 3 IMAX", 30, RoomType.ROOM_IMAX));


			// Seats for room 1

			for (int i = 1; i <= cinemaRoomOne.getCapacity() ; i++) {
				seatRepository.save(new Seat(i, cinemaRoomOne));

			}

			// Seats for room 2

			for (int i = 1; i <= cinemaRoomTwo.getCapacity() ; i++) {
				seatRepository.save(new Seat(i, cinemaRoomTwo));
			}


			// Seats for room 3

			for (int i = 1; i <= cinemaRoomThree.getCapacity() ; i++) {
				seatRepository.save(new Seat(i, cinemaRoomThree));
			}


			// Actores

			Actor harrisonFord = actorRepository.save(new Actor("Harrison", "Ford"));

			// Directores

			Director jamesMangold = directorRepository.save(new Director("James", "Mangold"));


			// PELICULAS

			Movie indianaJones2d = movieRepository.save(new Movie("https://boleteriacinerama.com.ar/assets/images/peliculas/64230120.jpg", "https://www.youtube.com/watch?v=dC1E_E78R48","Indiana Jones y el dia del destino", "Quinta entrega confirmada de Indiana Jones con Harrison Ford regresando en su icónico papel. La película estará dirigida por James Mangold (Ford vs Ferrari). Sin sinopsis por el momento.", MovieRestriction.RATED_PG, 152, MovieGenre.Adventure, MovieType.MOVIE_2D, Set.of(harrisonFord), jamesMangold));


			harrisonFord.getMovies().add(indianaJones2d);
			actorRepository.save(harrisonFord);



			// Creo un show

			Show showOne = showRepository.save(new Show(LocalDateTime.of(2023,07,15,19,00,00), LocalDateTime.of(2023,07,15,21,30,00), 1000.00, indianaJones2d, cinemaRoomOne));


			// Creo una compra

			Purchase purchaseOne = purchaseRepository.save(new Purchase());

			// Le asigno un cliente

			purchaseOne.setClient(clientOne);

			purchaseRepository.save(purchaseOne);

			// Averiguo la edad del cliente 1

			Integer clientAge = LocalDate.now().getYear() - clientOne.getBornDate().getYear();
			System.out.println("El cliente uno tiene " + clientAge);


			// Creo una variable y le asigno los asientos de la sala donde se va a hacer la función

			Set<Seat> roomOneSeats = cinemaRoomRepository.findById(showOne.getCinemaRoom().getId()).get().getSeats().stream().collect(Collectors.toSet());

			System.out.println(roomOneSeats.size() + " cantidad de asientos sala");


			// Creo una variable y le asigno los tickets que se hayan sacado para la funcion

			Set<Ticket> showOneTicketsSold = ticketRepository.findAll().stream().filter(ticket -> ticket.getShow().getId() == showOne.getId()).collect(Collectors.toSet());

			System.out.println(showOneTicketsSold.size() + " tickets vendidos para la funcion 1");




			// Creo una variable y le asigno los asientos de la sala

			Set<Seat> unassignedSeats = new HashSet<>(roomOneSeats);

			for (Ticket ticket : showOneTicketsSold) {
				Long seatId = ticket.getSeatId();

				// Filtro los asientos de la sala y solo dejo aquellos que no estén asignados a ningun ticket
				unassignedSeats.removeIf(seat -> seat.getId() == seatId);
			}

			System.out.println(unassignedSeats.size() + " cantidad de asientos no asignados");


			// Selecciono un asiento de los asientos no asignados

			Seat seatSelected = unassignedSeats.stream().findFirst().get();


			// Creo un ticket, le paso los parámetros del asiento y de la compra

			Ticket ticketForShowOne1 = ticketRepository.save(new Ticket(seatSelected.getId(), seatSelected.getSeatPlace(), purchaseOne, showOne));

			ticketForShowOne1.setTicketPrice(ticketForShowOne1.getShow().getStandardPrice());
			ticketForShowOne1.addPriceToPurchase();

			// Actualizo los tickets vendidos para la funcion 1

			showOneTicketsSold = ticketRepository.findAll().stream().filter(ticket -> ticket.getShow().getId() == showOne.getId()).collect(Collectors.toSet());

			System.out.println(showOneTicketsSold.size() + " tickets vendidos para la funcion 1");

			// Vuelvo a iterar los tickets vendidos para la funcion 1

			for (Ticket ticket : showOneTicketsSold) {
				Long seatId = ticket.getSeatId();

				// Filtro los asientos de la sala y solo dejo aquellos que no estén asignados a ningun ticket
				unassignedSeats.removeIf(seat -> seat.getId() == seatId);
			}

			System.out.println(unassignedSeats.size() + " cantidad de asientos no asignados");


			// Selecciono un asiento de los asientos no asignados

			Seat seatSelected2 = unassignedSeats.stream().findFirst().get();

			// Creo un ticket, le paso los parámetros del asiento y de la compra

			Ticket ticket2ForShowOne1 = ticketRepository.save(new Ticket(seatSelected2.getId(), seatSelected2.getSeatPlace(), purchaseOne, showOne));

			ticket2ForShowOne1.setTicketPrice(ticket2ForShowOne1.getShow().getStandardPrice());
			ticket2ForShowOne1.addPriceToPurchase();

			// Actualizo los tickets vendidos para la funcion 1

			showOneTicketsSold = ticketRepository.findAll().stream().filter(ticket -> ticket.getShow().getId() == showOne.getId()).collect(Collectors.toSet());

			System.out.println(showOneTicketsSold.size() + " tickets vendidos para la funcion 1");

			// Vuelvo a iterar los tickets vendidos para la funcion 1

			for (Ticket ticket : showOneTicketsSold) {
				Long seatId = ticket.getSeatId();

				// Filtro los asientos de la sala y solo dejo aquellos que no estén asignados a ningun ticket
				unassignedSeats.removeIf(seat -> seat.getId() == seatId);
			}

			System.out.println(unassignedSeats.size() + " cantidad de asientos no asignados");


			// Productos

			Product cocaChica = productRepository.save(new Product("Coca-cola vaso chico", 200.00, 20, ProductType.DRINK, 236));

			Product cocaMedia = productRepository.save(new Product("Coca-cola vaso mediano", 300.00, 20, ProductType.DRINK, 354));

			Product cocaGrande = productRepository.save(new Product("Coca-cola vaso grande", 350.00, 20, ProductType.DRINK, 473));

			Product pochocloChico = productRepository.save(new Product("Balde pochoclo chico", 500.00, 20, ProductType.FOOD, 300));

			Product pochocloMediano = productRepository.save(new Product("Balde pochoclo mediano", 700.00, 20, ProductType.FOOD, 500));

			Product pochocloGrande = productRepository.save(new Product("Balde pochoclo grande", 800.00, 20, ProductType.FOOD,800 ));


			PurchaseItem purchaseItem = purchaseItemRepository.save(new PurchaseItem(3,purchaseOne, cocaMedia));

			purchaseItem.addPriceToPurchase();
			purchaseItem.decreaseProductStock();
			productRepository.save(cocaMedia);



			PurchaseItem purchaseItem1 = purchaseItemRepository.save(new PurchaseItem(3,purchaseOne, pochocloMediano));
			purchaseItem1.addPriceToPurchase();
			purchaseItem1.decreaseProductStock();
			productRepository.save(pochocloMediano);


			purchaseRepository.save(purchaseOne);
		};

	}




}
