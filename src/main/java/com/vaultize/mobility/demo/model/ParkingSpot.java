package com.vaultize.mobility.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long parkingSpotId;

    private Float latitude;

    private Float longitude;

    private String address;

    @OneToMany(mappedBy = "parkingSpot", fetch = FetchType.LAZY)
    private Collection<ParkingReservation> parkingReservations = new ArrayList<>();

    public long getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(long parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<ParkingReservation> getParkingReservations() {
        return parkingReservations;
    }

    public void setParkingReservations(Collection<ParkingReservation> parkingReservations) {
        this.parkingReservations = parkingReservations;
    }
}
