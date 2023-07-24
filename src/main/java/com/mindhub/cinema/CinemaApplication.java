package com.mindhub.cinema;

import com.mindhub.cinema.models.*;
import com.mindhub.cinema.repositories.*;
import com.mindhub.cinema.services.servinterfaces.ClientServiceInterface;
import com.mindhub.cinema.utils.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ClientServiceInterface clientService;


	@Bean
	public CommandLineRunner initData(CinemaRoomRepository cinemaRoomRepository, ClientRepository clientRepository,  MovieRepository movieRepository, ProductRepository productRepository, ProductComboRepository productComboRepository, PurchaseItemRepository purchaseItemRepository, PurchaseRepository purchaseRepository, ReviewRepository reviewRepository, SeatRepository seatRepository, ShowRepository showRepository, TicketRepository ticketRepository) {
		return (args) -> {



			// save customers

			Client clientOne = clientRepository.save(new Client("Abdul", "Randall", "abdulrandall@gmail.com", "1234"));

			Client clientAdmin = clientRepository.save(new Client("admin", "admin", "cineadmin@cinema.com", "admin"));


			clientAdmin.setClientRol(ClientRol.ADMIN);
			clientRepository.save(clientAdmin);


			Client employee = clientRepository.save(new Client("Nannie", "Espinoza", "nannie_espinoza@gmail.com", "1234"));

			employee.setClientRol(ClientRol.EMPLOYEE);
			clientRepository.save(employee);

			/*
			Client clientTwo = clientRepository.save(new Client("Bobby", "Pearce", "bobby_pearce@gmail.com", "1234", LocalDate.of(1993, 07, 20)));





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





			// PELICULAS

			Movie indianaJones2d = movieRepository.save(new Movie("https://boleteriacinerama.com.ar/assets/images/peliculas/64230120.jpg", "https://www.youtube.com/watch?v=dC1E_E78R48","Indiana Jones y el dia del destino", "Quinta entrega confirmada de Indiana Jones con Harrison Ford regresando en su icónico papel. La película estará dirigida por James Mangold (Ford vs Ferrari). Sin sinopsis por el momento.", MovieRestriction.SAM_13, 152, "Castellano", MovieGenre.ADVENTURE, MovieType.MOVIE_2D, MovieAvailability.AVAILABLE));







			// Creo un show

			Show showOne = showRepository.save(new Show(LocalDateTime.of(2023,8,19,19,00,00), 1000.00, indianaJones2d, cinemaRoomOne));


			// Creo una compra

			Purchase purchaseOne = purchaseRepository.save(new Purchase());

			// Le asigno un cliente

			purchaseOne.setClient(clientOne);

			purchaseRepository.save(purchaseOne);





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

			Ticket ticketForShowOne1 = ticketRepository.save(new Ticket(seatSelected.getId(), seatSelected.getSeatPlace(),CustomerAge.ADULT, purchaseOne, showOne));

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

			Ticket ticket2ForShowOne1 = ticketRepository.save(new Ticket(seatSelected2.getId(), seatSelected2.getSeatPlace(), CustomerAge.ADULT, purchaseOne, showOne));

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



			// Product Combos



			ProductCombo productComboSingle = productComboRepository.save(new ProductCombo());

			productComboSingle.setTemplateCombo(true);
			productComboSingle.setProductComboType(ProductComboType.SINGLE);

			List<Product> comboSingleProducts = new ArrayList<>();

			comboSingleProducts.add(cocaMedia);
			comboSingleProducts.add(pochocloMediano);

			productComboSingle.setProducts(comboSingleProducts);

			productComboSingle.setComboDefaultPrice(cocaMedia.getProductPrice() + pochocloMediano.getProductPrice() );

			productComboSingle.updateComboPrice();

			productComboRepository.save(productComboSingle);




			ProductCombo comboElegido = productComboRepository.save(new ProductCombo());

			comboElegido.getProducts().addAll(comboSingleProducts);
			comboElegido.setProductComboType(ProductComboType.SINGLE);
			comboElegido.setPurchase(purchaseOne);
			comboElegido.setComboDefaultPrice(productComboSingle.getComboDefaultPrice());
			comboElegido.updateComboPrice();
			productComboRepository.save(comboElegido);



			// Otros Combos

			ProductCombo familyCombo = productComboRepository.save(new ProductCombo());

			familyCombo.setTemplateCombo(true);
			familyCombo.setProductComboType(ProductComboType.FAMILY);

			List<Product> comboFamilyProducts = new ArrayList<>();


			comboFamilyProducts.add(pochocloMediano);
			comboFamilyProducts.add(pochocloMediano);
			comboFamilyProducts.add(cocaMedia);
			comboFamilyProducts.add(cocaMedia);
			comboFamilyProducts.add(cocaMedia);
			comboFamilyProducts.add(cocaMedia);

			familyCombo.setProducts(comboFamilyProducts);

			for (Product product : comboFamilyProducts) {
				familyCombo.setComboDefaultPrice(familyCombo.getComboDefaultPrice() + product.getProductPrice());
			}

			familyCombo.updateComboPrice();
			productComboRepository.save(familyCombo);





			ProductCombo comboSingleMega = productComboRepository.save(new ProductCombo());

			comboSingleMega.setTemplateCombo(true);
			comboSingleMega.setProductComboType(ProductComboType.SINGLE_MEGA);

			List<Product> comboSingleMegaProducts = new ArrayList<>();

			comboSingleMegaProducts.add(pochocloGrande);
			comboSingleMegaProducts.add(cocaGrande);

			comboSingleMega.setProducts(comboSingleMegaProducts);

			comboSingleMega.setComboDefaultPrice(pochocloGrande.getProductPrice() + cocaGrande.getProductPrice());

			comboSingleMega.updateComboPrice();

			productComboRepository.save(comboSingleMega);

		};

	}




}
