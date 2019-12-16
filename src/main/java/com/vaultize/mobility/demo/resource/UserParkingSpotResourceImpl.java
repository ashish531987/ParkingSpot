package com.vaultize.mobility.demo.resource;

import com.vaultize.mobility.demo.resource.dto.request.ParkingSpotReservationRequestDTO;
import com.vaultize.mobility.demo.resource.dto.response.ParkingSpotResponseDTO;
import com.vaultize.mobility.demo.service.UserService;
import com.vaultize.mobility.demo.service.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = UserParkingSpotResourceImpl.REST_USERID_ENDPOINT_PREFIX)
public class UserParkingSpotResourceImpl implements UserParkingSpotResource {

    @Autowired
    UserService userService;

    public static final String REST_URL_PARAM_USER = "user_id";
    public static final String REST_URL_PARAM_PARKING_SPOT = "parking_spot_id";
    public static final String REST_USERID_ENDPOINT_PREFIX = "/users/{"+REST_URL_PARAM_USER+"}";
    public static final String REST_PARKING_RESERVATION_BY_ME_PREFIX = "/{"+REST_URL_PARAM_PARKING_SPOT+"}";

    public static final String REST_PARKING_GET_ALL_RESERVATION_BY_ME = "/reservations";
    public static final String REST_RESERVE_PARKING_API = REST_PARKING_GET_ALL_RESERVATION_BY_ME +
            REST_PARKING_RESERVATION_BY_ME_PREFIX;

    @Override
    @GetMapping(value=REST_PARKING_GET_ALL_RESERVATION_BY_ME,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getParkingReservations(@NotBlank @PathVariable(name=REST_URL_PARAM_USER) Long userId) {
        List<ParkingSpotResponseDTO> parkingSpotList = userService.getParkingReservations(userId);
        return ResponseEntity.ok(parkingSpotList);
    }

    @Override
    @PostMapping(
            value=REST_RESERVE_PARKING_API,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> reserveParking(@NotBlank @PathVariable(name=REST_URL_PARAM_USER) Long userId,
                                                 @NotBlank @PathVariable(name=REST_URL_PARAM_PARKING_SPOT) Long parkingSpotId,
                                                 @Valid @RequestBody ParkingSpotReservationRequestDTO parkingSpotReservationRequestDTO) {
        ParkingSpotResponseDTO parkingSpotResponseDTO = null;
        try {
            parkingSpotResponseDTO = userService.reserveParking(userId, parkingSpotId, parkingSpotReservationRequestDTO);
            return ResponseEntity.ok(parkingSpotResponseDTO);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @DeleteMapping(value=REST_RESERVE_PARKING_API, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> cancelReservation(Long userId, Long parkingSpotId) {
        ParkingSpotResponseDTO parkingSpotResponseDTO = null;
        try {
            parkingSpotResponseDTO = userService.cancelReservation(userId, parkingSpotId);
            return ResponseEntity.ok(parkingSpotResponseDTO);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
