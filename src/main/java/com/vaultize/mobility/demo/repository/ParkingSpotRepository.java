package com.vaultize.mobility.demo.repository;

import com.vaultize.mobility.demo.model.ParkingSpot;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    @EntityGraph(attributePaths = {"parkingReservations"})
    List<ParkingSpot> findAll();

    @Query(
            value = "SELECT * FROM parking_spot p LEFT JOIN parking_reservation b ON p.parking_spot_id = b.parking_spot_id where b.parking_spot_id IS NULL",
            nativeQuery = true)
    Optional<List<ParkingSpot>> getAvailable();

    @Query(
            value = "SELECT * FROM parking_spot p INNER JOIN parking_reservation b ON p.parking_spot_id = b.parking_spot_id",
            nativeQuery = true)
    Optional<List<ParkingSpot>> getReserved();

    @Query(
            value = "SELECT * FROM parking_spot p INNER JOIN parking_reservation b ON p.parking_spot_id = b.parking_spot_id where b.user_id = ?1",
            nativeQuery = true)
    Optional<List<ParkingSpot>> getReservedByUserId(Long userId);


}
