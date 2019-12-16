package com.vaultize.mobility.demo.service;

import com.vaultize.mobility.demo.model.ParkingSpot;
import com.vaultize.mobility.demo.model.User;
import com.vaultize.mobility.demo.resource.dto.request.ParkingSpotReservationRequestDTO;
import com.vaultize.mobility.demo.resource.dto.response.ParkingSpotResponseDTO;
import com.vaultize.mobility.demo.service.exceptions.InvalidInputException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * User service to fetch user specific reservations or do actions like reserve or cancel.
 */
public interface UserService {
    List<ParkingSpotResponseDTO> getParkingReservations(long userId);
    ParkingSpotResponseDTO reserveParking(long userId,
                                          long spotParkingId,
                                          ParkingSpotReservationRequestDTO parkingSpotReservationRequestDTO) throws InvalidInputException;
    ParkingSpotResponseDTO cancelReservation(long userId,
                                          long spotParkingId) throws InvalidInputException;
}
