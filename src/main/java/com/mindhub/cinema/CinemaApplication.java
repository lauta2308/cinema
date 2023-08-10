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

			Client clientOne = clientRepository.save(new Client("Abdul", "Randall", "abdulrandall@gmail.com", passwordEncoder.encode("1234")));

			Client clientAdmin = clientRepository.save(new Client("admin", "admin", "cineadmin@cinema.com", passwordEncoder.encode("admin")));


			clientAdmin.setClientRol(ClientRol.ADMIN);
			clientRepository.save(clientAdmin);


			Client employee = clientRepository.save(new Client("Nannie", "Espinoza", "nannie_espinoza@gmail.com", passwordEncoder.encode("1234")));

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

			Movie indianaJones2d = movieRepository.save(new Movie("https://images.tbco.app/blob-image/www.movienewsletters.net/photos/337713R1.jpg?width=270&height=405", "https://www.youtube.com/embed/dC1E_E78R48?controls=0","Indiana Jones and the Day of Destiny", "Archaeologist Indiana Jones races against time to retrieve a legendary artifact that can change the course of history.", MovieRestriction.SAM_13, 154, "Castellano", MovieGenre.ADVENTURE, MovieType.MOVIE_2D, MovieAvailability.AVAILABLE));


			Movie barbie = movieRepository.save(new Movie("https://images.tbco.app/blob-image/www.movienewsletters.net/photos/215333R1.jpg?width=270&height=405", "https://www.imdb.com/video/vi945734681/?ref_=sh_ov_vi","barbie", "Barbie suffers a crisis that leads her to question her world and her existence.", MovieRestriction.ATP, 114, "Castellano", MovieGenre.ADVENTURE, MovieType.MOVIE_2D, MovieAvailability.AVAILABLE));


			Movie cobweb = movieRepository.save(new Movie("https://images.tbco.app/blob-image/www.movienewsletters.net/photos/344228R1.jpg?width=270&height=405", "https://www.imdb.com/video/vi1253230361/?playlistId=tt9100018&ref_=tt_ov_vi","COBWEB", "Horror strikes when an eight-year-old boy named Peter tries to investigate the mysterious knocking noises that are coming from inside the walls of his house and a dark secret that his sinister parents kept hidden from him.", MovieRestriction.SAM_16, 152, "Castellano", MovieGenre.ADVENTURE, MovieType.MOVIE_2D, MovieAvailability.COMING_SOON));

			Movie oppenheimerImax = movieRepository.save(new Movie("https://images.tbco.app/blob-image/www.movienewsletters.net/photos/323982R1.jpg?width=270&height=405", "https://www.youtube.com/watch?v=dC1E_E78R48","OPPENHEIMER IMAX", "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.", MovieRestriction.SAM_13, 180, "Castellano", MovieGenre.DRAMA, MovieType.MOVIE_3D, MovieAvailability.AVAILABLE));

			Movie oppenheimer = movieRepository.save(new Movie("https://images.tbco.app/blob-image/www.movienewsletters.net/photos/347409R1.jpg?width=270&height=405", "https://www.youtube.com/watch?v=dC1E_E78R48","Oppenheimer 2D", "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.", MovieRestriction.SAM_13, 180, "Castellano", MovieGenre.DRAMA, MovieType.MOVIE_2D, MovieAvailability.AVAILABLE));



			Movie insidious = movieRepository.save(new Movie("https://images.tbco.app/blob-image/www.movienewsletters.net/photos/335198R1.jpg?width=270&height=405", "https://www.imdb.com/video/vi3679044633/?playlistId=tt13405778&ref_=tt_ov_vi","Insidious", "The Lamberts must go deeper into The Further than ever before to put their demons to rest once and for all.", MovieRestriction.SAM_13, 152, "Castellano", MovieGenre.HORROR, MovieType.MOVIE_2D, MovieAvailability.AVAILABLE));

			Movie soundOfFreedom = movieRepository.save(new Movie("https://images.tbco.app/blob-image/www.movienewsletters.net/photos/346813R1.jpg?width=270&height=405", "https://www.imdb.com/video/vi3898393625/?playlistId=tt7599146&ref_=tt_pr_ov_vi","Sound of freedom", "The incredible true story of a former government agent turned vigilante who embarks on a dangerous mission to rescue hundreds of children from sex traffickers.", MovieRestriction.SAM_13, 131, "Castellano", MovieGenre.ACTION, MovieType.MOVIE_2D, MovieAvailability.AVAILABLE));

			Movie elemental = movieRepository.save(new Movie("https://images.tbco.app/blob-image/www.movienewsletters.net/photos/332735R1.jpg?width=270&height=405", "https://www.imdb.com/video/vi2769667097/?playlistId=tt15789038&ref_=tt_pr_ov_vi","Elemental", "Follows Ember and Wade, in a city where fire-, water-, land- and air-residents live together.", MovieRestriction.ATP, 101, "Castellano", MovieGenre.ADVENTURE, MovieType.MOVIE_2D, MovieAvailability.AVAILABLE));


			Movie litleMermaid = movieRepository.save(new Movie("https://images.tbco.app/blob-image/www.movienewsletters.net/photos/324884R1.jpg?width=270&height=405", "https://www.imdb.com/video/vi1211810841/?playlistId=tt5971474&ref_=tt_pr_ov_vi","The litle mermaid", "A young mermaid makes a deal with a sea witch to trade her beautiful voice for human legs so she can discover the world above water and impress a prince.", MovieRestriction.SAM_13, 135, "Castellano", MovieGenre.ADVENTURE, MovieType.MOVIE_2D, MovieAvailability.AVAILABLE));





			// Creo un show

			Show showOne = showRepository.save(new Show(LocalDateTime.of(2023,8,19,16,00,00), 15.00, indianaJones2d, cinemaRoomOne));


			showRepository.save(new Show(LocalDateTime.of(2023,8,19,19,00,00), 15.00, indianaJones2d, cinemaRoomOne));

			showRepository.save(new Show(LocalDateTime.of(2023,8,19,22,00,00), 15.00, indianaJones2d, cinemaRoomOne));

			// Creo una compra

			Purchase purchaseOne = purchaseRepository.save(new Purchase());
			purchaseOne.setPurchaseStatus(PurchaseStatus.COMPLETED);

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

			Product cocaChica = productRepository.save(new Product("Small Coca-Cola glass", 4.00,  ProductType.DRINK, 236));

			Product cocaMedia = productRepository.save(new Product("Medium Coca-Cola glass", 6.00,  ProductType.DRINK, 354));

			Product cocaGrande = productRepository.save(new Product("Large Coca-Cola glass", 7.00, ProductType.DRINK, 473));

			Product pochocloChico = productRepository.save(new Product("Small popcorn bucket", 5.00, ProductType.FOOD, 300));

			Product pochocloMediano = productRepository.save(new Product("Medium popcorn bucket", 7.00, ProductType.FOOD, 500));

			Product pochocloGrande = productRepository.save(new Product("Large popcorn bucket", 8.00, ProductType.FOOD,800 ));


			PurchaseItem purchaseItem = purchaseItemRepository.save(new PurchaseItem(3,purchaseOne, cocaMedia));

			purchaseItem.addPriceToPurchase();

			productRepository.save(cocaMedia);



			PurchaseItem purchaseItem1 = purchaseItemRepository.save(new PurchaseItem(3,purchaseOne, pochocloMediano));
			purchaseItem1.addPriceToPurchase();
			productRepository.save(pochocloMediano);


			purchaseRepository.save(purchaseOne);


			Purchase adminPurchase = purchaseRepository.save(new Purchase(clientAdmin));

			// Product Combos



			ProductCombo productComboSingle = productComboRepository.save(new ProductCombo());

			productComboSingle.setPurchase(adminPurchase);
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

			familyCombo.setPurchase(adminPurchase);
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

			comboSingleMega.setPurchase(adminPurchase);
			comboSingleMega.setTemplateCombo(true);
			comboSingleMega.setProductComboType(ProductComboType.SINGLE_MEGA);

			List<Product> comboSingleMegaProducts = new ArrayList<>();

			comboSingleMegaProducts.add(pochocloGrande);
			comboSingleMegaProducts.add(cocaGrande);

			comboSingleMega.setProducts(comboSingleMegaProducts);

			comboSingleMega.setComboDefaultPrice(pochocloGrande.getProductPrice() + cocaGrande.getProductPrice());

			comboSingleMega.updateComboPrice();

			productComboRepository.save(comboSingleMega);




			reviewRepository.save(new Review(5, "Very good", clientOne, indianaJones2d));


			Review review2 = reviewRepository.save(new Review(5, "Very after", clientOne, indianaJones2d));

			review2.setReviewDate(LocalDateTime.now().plusHours(5));

			reviewRepository.save(review2);









		};




	}




}
