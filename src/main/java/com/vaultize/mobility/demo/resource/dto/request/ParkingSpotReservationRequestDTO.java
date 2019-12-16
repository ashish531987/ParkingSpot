package com.vaultize.mobility.demo.resource.dto.request;

public class ParkingSpotReservationRequestDTO extends AbstractRequestDTO {
    private float cost;

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
