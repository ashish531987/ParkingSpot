package com.vaultize.mobility.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String name;

    private String mobile;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Collection<ParkingReservation> parkingReservations = new ArrayList<>();


    public Collection<ParkingReservation> getParkingReservations() {
        return parkingReservations;
    }

    public void setParkingReservations(Collection<ParkingReservation> parkingReservations) {
        this.parkingReservations = parkingReservations;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
