package com.vaultize.mobility.demo.service;

import com.vaultize.mobility.demo.resource.dto.request.ParkingSpotSearchRequestDTO;
import com.vaultize.mobility.demo.resource.dto.response.ParkingSpotResponseDTO;

import java.util.List;

/**
 * Parking Spot service - Responsible for managing Parking spots.
 */
public interface ParkingSpotService {

    /**
     * Find All parking spots.
     * @return List of {@link ParkingSpotResponseDTO} all parking spots.*/
    List<ParkingSpotResponseDTO> findAllWithoutNPlusOne();

    /**
     * Find only available parking spots.
     * @return List of {@link ParkingSpotResponseDTO} available parking spots. */
    List<ParkingSpotResponseDTO> getAvailable();

    /**
     * Find only reserved parking spots.
     * @return List of {@link ParkingSpotResponseDTO} available parking spots.     */
    List<ParkingSpotResponseDTO> getReserved();

    /**
     * Find all parking spots with a given address - Lat, Long and Radius.
     * @param parkingSpotSearchRequestDTO of {@link {@link ParkingSpotSearchRequestDTO}} - Lat, Long and Radius
     *  @return List of {@link ParkingSpotResponseDTO} available parking spots.     */
    List<ParkingSpotResponseDTO> search(ParkingSpotSearchRequestDTO parkingSpotSearchRequestDTO);
}
