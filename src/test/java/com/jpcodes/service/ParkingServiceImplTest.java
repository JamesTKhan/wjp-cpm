package com.jpcodes.service;

import com.jpcodes.model.ParkingSpace;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ParkingServiceImplTest {

    private ParkingServiceImpl parkingService;

    ParkingSpace[] testParkingSpaces = {
            new ParkingSpace(5000, "ABC"),
            new ParkingSpace(5001, "XYZ"),
            new ParkingSpace(5002, "NEW"),
            new ParkingSpace(5003, "CAR"),
            new ParkingSpace(5004, "POL"),
            new ParkingSpace(5005, "316"),
            new ParkingSpace(5006, "TDS"),
            new ParkingSpace(5007, "OKL"),
            new ParkingSpace(5008, "LOL"),
            new ParkingSpace(5009, "HUH"),
    };

    @Before
    public void setup() {
        int MAX_SPACES = 10;
        int TICKET_START = 5000;
        String DELIMITER = ",";
        parkingService = new ParkingServiceImpl(MAX_SPACES, DELIMITER, TICKET_START);
    }

    @Test
    public void testParkCar() {
        parkAllCars();

        ParkingSpace[] returnedSpaces = parkingService.getParkingSpaces();

        for (int i = 0; i < testParkingSpaces.length; i++) {
            assertEquals(testParkingSpaces[i].getTicketNumber(), returnedSpaces[i].getTicketNumber());
            assertEquals(testParkingSpaces[i].getTag(), returnedSpaces[i].getTag());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkCarMoreThanMaxSpaces() {
        // park 10 cars
        for (ParkingSpace testParkingSpace : testParkingSpaces) {
            parkingService.parkCar(testParkingSpace.getTag());
        }

        // Park a car over maximum
        parkingService.parkCar("TES");
    }

    @Test
    public void testUnparkCar() {
        parkAllCars();

        parkingService.unParkCar(5000);
        parkingService.unParkCar(5005);

        ParkingSpace[] returnedSpaces = parkingService.getParkingSpaces();
        assertNull(returnedSpaces[0]);
        assertNull(returnedSpaces[5]);
    }

    @Test
    public void testUnparkCarNotFound() {
        // Setup new output stream for capturing System output
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream initialOut = System.out;

        System.setOut(new PrintStream(outStream));

        parkingService.unParkCar(5000);

        // capture the output
        String output = outStream.toString();

        assertTrue(output.contains("Car with ticket number 5000 is not in the parking lot."));

        System.setOut(initialOut);
    }

    @Test
    public void testCompactCars() {
        parkAllCars();

        parkingService.unParkCar(5000);
        parkingService.unParkCar(5005);

        parkingService.compactCars();

        ParkingSpace[] returnedSpaces = parkingService.getParkingSpaces();

        // spot one should have shifted left once
        assertEquals(testParkingSpaces[1].getTag(), returnedSpaces[0].getTag());
        assertEquals(testParkingSpaces[1].getTicketNumber(), returnedSpaces[0].getTicketNumber());

        // same with spot 6, shifted twice due to above unpark
        assertEquals(testParkingSpaces[6].getTag(), returnedSpaces[4].getTag());
        assertEquals(testParkingSpaces[6].getTicketNumber(), returnedSpaces[4].getTicketNumber());

        // and last two spots should now be null
        assertNull(returnedSpaces[8]);
        assertNull(returnedSpaces[9]);
    }
    @Test
    public void testPrintParkingSpaces() {
        // Setup new output stream for capturing System output
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream initialOut = System.out;

        System.setOut(new PrintStream(outStream));

        parkAllCars();

        parkingService.printParkingSpaces();

        // capture the output
        String output = outStream.toString();

        assertTrue(output.contains("ABC,XYZ,NEW,CAR,POL,316,TDS,OKL,LOL,HUH"));

        System.setOut(initialOut);

    }


    private void parkAllCars() {
        // park 10 cars
        for (ParkingSpace testParkingSpace : testParkingSpaces) {
            parkingService.parkCar(testParkingSpace.getTag());
        }
    }
}