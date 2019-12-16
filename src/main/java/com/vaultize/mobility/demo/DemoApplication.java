package com.vaultize.mobility.demo;

import com.vaultize.mobility.demo.model.ParkingReservation;
import com.vaultize.mobility.demo.model.ParkingSpot;
import com.vaultize.mobility.demo.model.User;
import com.vaultize.mobility.demo.repository.ParkingSpotRepository;
import com.vaultize.mobility.demo.repository.ReservationRepository;
import com.vaultize.mobility.demo.repository.UserRepository;
import com.vaultize.mobility.demo.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	UserRepository userRepository;


	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ParkingSpotRepository parkingSpotRepository;

	@Autowired
	ParkingSpotService parkingSpotService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	void populateDB(){
		User user1 = new User();
		user1.setName("Lionel Messi");
		user1.setMobile("1010101010");

		User user2 = new User();
		user2.setName("Jordi Alba");
		user2.setMobile("9898989899");

		// Shivaji Nagar
		ParkingSpot shivajiNagar = new ParkingSpot();
		shivajiNagar.setAddress("Shivaji Nagar");
		shivajiNagar.setLatitude((float) 18.526110);
		shivajiNagar.setLongitude((float)73.844131);


		// wakad
		ParkingSpot wakad = new ParkingSpot();
		wakad.setAddress("Wakad");
		wakad.setLatitude((float)18.596340);
		wakad.setLongitude((float)73.771889);

		// Shanipar
		ParkingSpot shanipar = new ParkingSpot();
		shanipar.setAddress("Shanipar");
		shanipar.setLatitude((float)18.517230);
		shanipar.setLongitude((float)73.853400);

		// Sahakar Nagar
		ParkingSpot sahakarNagar = new ParkingSpot();
		sahakarNagar.setAddress("Sahakar Nagar");
		sahakarNagar.setLatitude((float)18.489480);
		sahakarNagar.setLongitude((float)73.851870);

		// Katraj
		ParkingSpot katraj = new ParkingSpot();
		katraj.setAddress("Katraj");
		katraj.setLatitude((float)18.445120);
		katraj.setLongitude((float)73.861620);


		// Reservation
		ParkingReservation shivajiNagarReservation = new ParkingReservation();
		shivajiNagarReservation.setCost(20);
		shivajiNagarReservation.setParkingSpot(shivajiNagar);
		shivajiNagarReservation.setUser(user1);
		user1.getParkingReservations().add(shivajiNagarReservation);
		shivajiNagar.getParkingReservations().add(shivajiNagarReservation);

		ParkingReservation sahakarNagarReservation = new ParkingReservation();
		sahakarNagarReservation.setCost(15);
		sahakarNagarReservation.setParkingSpot(sahakarNagar);
		sahakarNagarReservation.setUser(user1);
		user1.getParkingReservations().add(sahakarNagarReservation);
		shivajiNagar.getParkingReservations().add(sahakarNagarReservation);

		ParkingReservation katrajReservation = new ParkingReservation();
		katrajReservation.setCost(10);
		katrajReservation.setParkingSpot(katraj);
		katrajReservation.setUser(user1);
		user1.getParkingReservations().add(katrajReservation);
		shivajiNagar.getParkingReservations().add(katrajReservation);


		userRepository.save(user1);
		userRepository.save(user2);
		parkingSpotRepository.save(shivajiNagar);
		parkingSpotRepository.save(wakad);
		parkingSpotRepository.save(shanipar);
		parkingSpotRepository.save(sahakarNagar);
		parkingSpotRepository.save(katraj);
		reservationRepository.save(shivajiNagarReservation);
		reservationRepository.save(sahakarNagarReservation);
		reservationRepository.save(katrajReservation);
//
		Logger logger = Logger.getLogger(DemoApplication.class.getName());
//
//		logger.info(" ---------------------- All Parking Spots ----------------------");
//
//		parkingSpotsService.findAllWithoutNPlusOne().forEach(p -> logger.info(p.getAddress()));
//
//		logger.info(" ---------------------- Available Parking Spots ----------------------");
//
//		parkingSpotsService.getAvailable().forEach(p -> logger.info(p.getAddress()));
//
//		logger.info(" ---------------------- Booked Parking Spots ----------------------");
//
//		parkingSpotsService.getReserved().forEach(p -> logger.info(p.getAddress()));
//
////		logger.info(" ---------------------- Users  ----------------------");
//
//		userRepository.findAll().forEach(user -> logger.info(user.getParkingReservations().size()+", "+user.getName()));

//		Geofencer geofencer = new Geofencer((float)18.562120, (float)73.802544, 5);
//		geofencer.findWithinFence(parkingSpotService.findAllWithoutNPlusOne()).forEach(p -> logger.info(p.getAddress()));
	}
}
