package com.vaultize.mobility.demo.geoutils;

import com.vaultize.mobility.demo.model.ParkingSpot;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Ashish
 *
 */
public class Geofencer {
    private float lat;
    private float lng;
    //in KM
    private float radius;
    public static final float DEFAULT_RADIUS_IN_KM = 5;


    /**
     * @param lat user's latitude
     * @param lng user's longitude
     * @param radius in Kilometers
     */
    public Geofencer(float lat, float lng, float radius) {
        this.lat = lat;
        this.lng = lng;
        this.radius = radius;
    }


    /**
     * @param lat user's latitude
     * @param lng user's longitude
     */
    public Geofencer(float lat, float lng) {
        this(lat, lng, DEFAULT_RADIUS_IN_KM);
    }


    /**
     *
     * @param {@link {@link com.vaultize.mobility.demo.model.User}'s preferred lat and long
     * @return list of parking spots within given radius from lat, long.
     */
    public List<ParkingSpot> findWithinFence(List<ParkingSpot> list){
        LogManager.getLogger().info("Finding all the parking spots within radius of:"+radius+" from:"+lat+", "+lng);

        List<ParkingSpot> results = new ArrayList<>();
        for(ParkingSpot parkingSpot : list){
            if(isInFence(parkingSpot.getLatitude(), parkingSpot.getLongitude())){
                LogManager.getLogger().info("Parking spot found within radius:"+parkingSpot.getAddress());
                results.add(parkingSpot);
            }
        }
        LogManager.getLogger().info("Num parking spots found within radius:"+results.size());
        return results;
    }


    private boolean isInFence(float vlat, float vlng){
        double theta = vlng - lng;
        double dist = Math.sin(deg2rad(vlat)) * Math.sin(deg2rad(lat)) + Math.cos(deg2rad(vlat)) * Math.cos(deg2rad(lat)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        //to convert to KM
        dist = dist * 1.609344;
        return (dist <= radius);
    }


    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::	This function converts decimal degrees to radians						 :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::	This function converts radians to decimal degrees						 :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}