package com.vaultize.mobility.demo.resource;

import com.vaultize.mobility.demo.resource.dto.request.ParkingSpotReservationRequestDTO;
import org.springframework.http.ResponseEntity;

public interface UserParkingSpotResource {
    ResponseEntity<Object> getParkingReservations(Long userId);
    ResponseEntity<Object> reserveParking(Long userId,
                                          Long parkingSpotId,
                                          ParkingSpotReservationRequestDTO parkingSpotReservationRequestDTO);

    ResponseEntity<Object> cancelReservation(Long userId, Long parkingSpotId);
}
