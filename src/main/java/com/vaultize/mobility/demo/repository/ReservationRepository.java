package com.vaultize.mobility.demo.repository;

import com.vaultize.mobility.demo.model.ParkingReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ParkingReservation, Long> {

    @Query(
            value = "delete from parking_reservation where user_id = ?1 and parking_spot_id = ?2",
            nativeQuery = true
    )
    ParkingReservation deleteAllByUserIdAndParkingSpotId(Long userId, Long parkingSpotId);
}
