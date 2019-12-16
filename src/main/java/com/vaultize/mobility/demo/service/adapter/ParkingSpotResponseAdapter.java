package com.vaultize.mobility.demo.service.adapter;

import com.vaultize.mobility.demo.model.ParkingSpot;
import com.vaultize.mobility.demo.resource.dto.response.ParkingSpotResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpotResponseAdapter {
    public List<ParkingSpotResponseDTO> buildResponse(List<ParkingSpot> list){
        List<ParkingSpotResponseDTO> dtos = new ArrayList<>(list.size());
        for (ParkingSpot parkingSpot : list) {
            ParkingSpotResponseDTO dto = adaptToParkingSpotResponseDTO(parkingSpot);
            dtos.add(dto);
        }
        return dtos;
    }

    public ParkingSpotResponseDTO adaptToParkingSpotResponseDTO(ParkingSpot parkingSpot) {
        ParkingSpotResponseDTO dto = new ParkingSpotResponseDTO();
        dto.setId(parkingSpot.getParkingSpotId());
        dto.setLatitude(parkingSpot.getLatitude());
        dto.setLongitude(parkingSpot.getLongitude());
        dto.setAddress(parkingSpot.getAddress());
        return dto;
    }
}
