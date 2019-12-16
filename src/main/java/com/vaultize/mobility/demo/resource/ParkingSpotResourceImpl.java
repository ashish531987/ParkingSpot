package com.vaultize.mobility.demo.resource;

import com.vaultize.mobility.demo.resource.dto.request.ParkingSpotSearchRequestDTO;
import com.vaultize.mobility.demo.resource.dto.response.ParkingSpotResponseDTO;
import com.vaultize.mobility.demo.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = ParkingSpotResourceImpl.REST_PARKINGS_ENDPOINT_PREFIX)
public class ParkingSpotResourceImpl implements ParkingSpotResource{

    @Autowired
    ParkingSpotService parkingSpotService;

    public static final String REST_PARKINGS_ENDPOINT_PREFIX = "/parkings";
    public static final String REST_PARKING_GET_ALL = REST_PARKINGS_ENDPOINT_PREFIX;
    public static final String REST_PARKING_GET_ALL_AVAILABLE = "/available";
    public static final String REST_PARKING_GET_ALL_RESERVED = "/reserved";
    public static final String REST_PARKING_SEARCH = "/search";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllParkingSpots() {
        List<ParkingSpotResponseDTO> parkingSpotList = parkingSpotService.findAllWithoutNPlusOne();
        return ResponseEntity.ok(parkingSpotList);
    }

    @GetMapping(value = REST_PARKING_GET_ALL_AVAILABLE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllAvailableParkingSpots() {
        List<ParkingSpotResponseDTO> parkingSpotList = parkingSpotService.getAvailable();
        return ResponseEntity.ok(parkingSpotList);
    }

    @GetMapping(value = REST_PARKING_GET_ALL_RESERVED, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllReservedParkingSpots() {
        List<ParkingSpotResponseDTO> parkingSpotList = parkingSpotService.getReserved();
        return ResponseEntity.ok(parkingSpotList);
    }

    @PostMapping(value = REST_PARKING_SEARCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> search(@Valid @RequestBody ParkingSpotSearchRequestDTO parkingSpotSearchRequestDTO) {
        List<ParkingSpotResponseDTO> parkingSpotList = parkingSpotService.search(parkingSpotSearchRequestDTO);
        return ResponseEntity.ok(parkingSpotList);
    }

}
