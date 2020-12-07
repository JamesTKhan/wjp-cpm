package com.jpcodes.service;

import com.jpcodes.message.ErrorMessageEnum;
import com.jpcodes.model.ParkingSpace;

public class ParkingServiceImpl implements ParkingService {

    private final int maxSpaces;
    private final String delimiter;
    private int currentTicket;

    // Declare array of tracked parking spaces
    private ParkingSpace[] parkingSpaces;

    public ParkingServiceImpl(int maxSpaces, String delimiter, int ticketStart) {
        this.maxSpaces = maxSpaces;
        this.delimiter = delimiter;
        this.parkingSpaces = new ParkingSpace[maxSpaces];
        this.currentTicket = ticketStart;
    }

    @Override
    public void parkCar(String tag) throws IllegalArgumentException {
        boolean carParked = false;
        for (int i = 0; i < parkingSpaces.length; i++) {
            if (parkingSpaces[i] == null) {
                parkingSpaces[i] = new ParkingSpace(currentTicket, tag);
                carParked = true;
                break;
            }
        }

        if (!carParked) {
            // No empty spaces were found, parking lot is full
            throw new IllegalArgumentException(ErrorMessageEnum.GARAGE_FULL.getDescription());
        } else {
            currentTicket++;
        }
    }

    @Override
    public void unParkCar(int ticketNumber) {
        boolean carFound = false;
        for (int i = 0; i < parkingSpaces.length; i++) {
            if (parkingSpaces[i] != null && parkingSpaces[i].getTicketNumber() == ticketNumber) {
                // Found the car, lets un-park it by setting value back to null
                parkingSpaces[i] = null;
                carFound = true;
                break;
            }
        }

        if (!carFound) {
            System.out.println(ErrorMessageEnum.CAR_NOT_FOUND.getDescription(String.valueOf(ticketNumber)));
        }
    }

    @Override
    public void compactCars() {
        // New array of compacted cars
        ParkingSpace[] compactedSpaces = new ParkingSpace[maxSpaces];

        // for each space in our new compact array...
        for (int i = 0; i < compactedSpaces.length; i++) {

            // for each element in the non-compact array..
            for (int j = 0; j < parkingSpaces.length; j++) {

                if (parkingSpaces[j] != null) {
                    // move the car to the compacted space and set value to null
                    compactedSpaces[i] = parkingSpaces[j];
                    parkingSpaces[j] = null;
                    break;
                }

            }

        }

        // Replace existing array with compacted array
        this.parkingSpaces = compactedSpaces;
    }

    @Override
    public void printParkingSpaces() {
        boolean first = true; // flag for comma printing

        // Print out the parking spaces
        for (ParkingSpace parkingSpace : parkingSpaces) {
            if (!first) {
                System.out.print(delimiter);
            }
            first = false;

            if (parkingSpace != null) {
                System.out.print(parkingSpace.getTag());
            }

        }
        System.out.println();
    }

    public ParkingSpace[] getParkingSpaces() {
        return parkingSpaces;
    }
}