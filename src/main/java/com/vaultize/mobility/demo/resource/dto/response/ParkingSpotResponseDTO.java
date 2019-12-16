package com.vaultize.mobility.demo.resource.dto.response;

public class ParkingSpotResponseDTO extends AbstractResponseDTO{
    private float latitude;
    private float longitude;
    private String address;
    private long id;

    public float getLatitude() {
        return latitude;
    }
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
    public float getLongitude() {
        return longitude;
    }
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }}
