package com.vaultize.mobility.demo.resource;

import com.vaultize.mobility.demo.resource.dto.request.ParkingSpotSearchRequestDTO;
import org.springframework.http.ResponseEntity;

/**
 * Parking Resource contract.
 */
public interface ParkingSpotResource {

    ResponseEntity<Object> getAllParkingSpots();
    ResponseEntity<Object> getAllAvailableParkingSpots();
    ResponseEntity<Object> getAllReservedParkingSpots();
    ResponseEntity<Object> search(ParkingSpotSearchRequestDTO parkingSpotSearchRequestDTO);
}
