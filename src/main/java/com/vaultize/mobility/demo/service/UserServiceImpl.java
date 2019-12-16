package com.vaultize.mobility.demo.service;

import com.vaultize.mobility.demo.model.ParkingReservation;
import com.vaultize.mobility.demo.model.ParkingSpot;
import com.vaultize.mobility.demo.model.User;
import com.vaultize.mobility.demo.repository.ParkingSpotRepository;
import com.vaultize.mobility.demo.repository.ReservationRepository;
import com.vaultize.mobility.demo.repository.UserRepository;
import com.vaultize.mobility.demo.resource.dto.request.ParkingSpotReservationRequestDTO;
import com.vaultize.mobility.demo.resource.dto.response.ParkingSpotResponseDTO;
import com.vaultize.mobility.demo.service.adapter.ParkingSpotResponseAdapter;
import com.vaultize.mobility.demo.service.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public List<ParkingSpotResponseDTO> getParkingReservations(long userId) {
        return new ParkingSpotResponseAdapter().buildResponse(parkingSpotRepository.getReservedByUserId(userId).orElse(new ArrayList<>()));
    }

    @Override
    @Transactional
    public ParkingSpotResponseDTO reserveParking(long userId,
                                                 long spotParkingId,
                                                 ParkingSpotReservationRequestDTO parkingSpotReservationRequestDTO) throws InvalidInputException {

        Optional<User> userOptional = userRepository.findById(userId);
        Optional<ParkingSpot> parkingSpotOptional = parkingSpotRepository.findById(spotParkingId);
        ParkingSpot parkingSpot = new ParkingSpot();

        if(userOptional.isPresent() && parkingSpotOptional.isPresent()){
            ParkingReservation reservation = new ParkingReservation();
            reservation.setCost(10);
            reservation.setParkingSpot(parkingSpotOptional.get());
            reservation.setUser(userOptional.get());

            userOptional.get().getParkingReservations().add(reservation);
            parkingSpotOptional.get().getParkingReservations().add(reservation);
            userRepository.save(userOptional.get());
            parkingSpot = parkingSpotRepository.save(parkingSpotOptional.get());
            reservationRepository.save(reservation);
        } else {
            throw new InvalidInputException();
        }
        return new ParkingSpotResponseAdapter().adaptToParkingSpotResponseDTO(parkingSpot);
    }

    @Override
    public ParkingSpotResponseDTO cancelReservation(long userId, long spotParkingId) throws InvalidInputException {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<ParkingSpot> parkingSpotOptional = parkingSpotRepository.findById(spotParkingId);

        if(userOptional.isPresent() && parkingSpotOptional.isPresent()) {
            ParkingReservation parkingReservation = reservationRepository.deleteAllByUserIdAndParkingSpotId(userId, spotParkingId);
            return new ParkingSpotResponseAdapter().adaptToParkingSpotResponseDTO(parkingSpotOptional.get());
        } else {
            throw new InvalidInputException();
        }

    }
}
