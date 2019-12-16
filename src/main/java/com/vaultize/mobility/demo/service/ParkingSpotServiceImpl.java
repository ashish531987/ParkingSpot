package com.vaultize.mobility.demo.service;

import com.vaultize.mobility.demo.geoutils.Geofencer;
import com.vaultize.mobility.demo.repository.ParkingSpotRepository;
import com.vaultize.mobility.demo.resource.dto.request.ParkingSpotSearchRequestDTO;
import com.vaultize.mobility.demo.resource.dto.response.ParkingSpotResponseDTO;
import com.vaultize.mobility.demo.service.adapter.ParkingSpotResponseAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {
    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    @Override
    public List<ParkingSpotResponseDTO> findAllWithoutNPlusOne() {
        return new ParkingSpotResponseAdapter().buildResponse(parkingSpotRepository.findAll());
    }

    @Override
    public List<ParkingSpotResponseDTO> getAvailable() {
        return new ParkingSpotResponseAdapter().buildResponse(parkingSpotRepository.getAvailable().orElse(new ArrayList<>()));
    }

    @Override
    public List<ParkingSpotResponseDTO> getReserved() {
        return new ParkingSpotResponseAdapter().buildResponse(parkingSpotRepository.getReserved().orElse(new ArrayList<>()));
    }

    @Override
    public List<ParkingSpotResponseDTO> search(ParkingSpotSearchRequestDTO parkingSpotSearchRequestDTO) {
            // Create bean
            Geofencer geofencer = new Geofencer(
                    parkingSpotSearchRequestDTO.getLatitude(),
                    parkingSpotSearchRequestDTO.getLongitude(),
                    parkingSpotSearchRequestDTO.getRadius());
        return new ParkingSpotResponseAdapter().buildResponse(geofencer.findWithinFence(parkingSpotRepository.getAvailable().orElse(new ArrayList<>())));
    }
}
