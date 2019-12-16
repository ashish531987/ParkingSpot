package com.vaultize.mobility.demo.geoutils;

import com.vaultize.mobility.demo.model.ParkingReservation;
import com.vaultize.mobility.demo.model.ParkingSpot;
import com.vaultize.mobility.demo.model.User;
import com.vaultize.mobility.demo.resource.dto.response.ParkingSpotResponseDTO;
import com.vaultize.mobility.demo.service.adapter.ParkingSpotResponseAdapter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GeofencerTest {
    private ParkingSpot shivajiNagar = new ParkingSpot();
    private ParkingSpot wakad = new ParkingSpot();
    private ParkingSpot shanipar = new ParkingSpot();
    private ParkingSpot sahakarNagar = new ParkingSpot();

    private List<ParkingSpot> availableParkingList = new ArrayList<>();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        // Shivaji Nagar
        shivajiNagar.setAddress("Shivaji Nagar");
        shivajiNagar.setLatitude((float) 18.526110);
        shivajiNagar.setLongitude((float) 73.844131);


        // wakad
        wakad.setAddress("Wakad");
        wakad.setLatitude((float) 18.596340);
        wakad.setLongitude((float) 73.771889);

        // Shanipar
        shanipar.setAddress("Shanipar");
        shanipar.setLatitude((float) 18.517230);
        shanipar.setLongitude((float) 73.853400);

        // Sahakar Nagar
        sahakarNagar.setAddress("Sahakar Nagar");
        sahakarNagar.setLatitude((float) 18.489480);
        sahakarNagar.setLongitude((float) 73.851870);

        availableParkingList.add(shivajiNagar);
        availableParkingList.add(wakad);
        availableParkingList.add(shanipar);
        availableParkingList.add(sahakarNagar);
    }

    @Test
    public void testSuccessfulSearchSingleResult(){
        Geofencer geofencer = new Geofencer((float) 18.596340, (float) 73.771889, 5); // Wakad lat long, nobody is in range.
        List<ParkingSpot> outputList =  geofencer.findWithinFence(availableParkingList);
        Assert.assertEquals(wakad.getAddress(), outputList.get(0).getAddress());
    }

    @Test
    public void testSuccessfulSearchMultipleResult(){
        Geofencer geofencer = new Geofencer((float) 18.517230, (float) 73.853400, 5); // Wakad lat long, nobody is in range.
        List<ParkingSpot> outputList =  geofencer.findWithinFence(availableParkingList);
        Assert.assertEquals(3, outputList.size());
    }
}
