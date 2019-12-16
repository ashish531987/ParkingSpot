package com.vaultize.mobility.demo.model;

import javax.persistence.*;

@Entity
public class ParkingReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long parkingReservationId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private long cost;

    @ManyToOne
    @JoinColumn(name = "parkingSpotId")
    private ParkingSpot parkingSpot;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getParkingReservationId() {
        return parkingReservationId;
    }

    public void setParkingReservationId(long parkingReservationId) {
        this.parkingReservationId = parkingReservationId;
    }


}
