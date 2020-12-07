package com.jpcodes.service;

import com.jpcodes.model.ParkingSpace;

public interface ParkingService {

    /**
     * Parks the car with the given ticket number and tag
     *
     * @param tag license tag of car being parked
     * @throws IllegalStateException exception if parking lot is full
     */
    void parkCar(String tag) throws IllegalArgumentException;

    /**
     * Unparks the car with the given ticketNumber
     *
     * @param ticketNumber ticket number of car to un-park
     */
    void unParkCar(int ticketNumber);

    /**
     * Compacts all car spaces leaving no empty spaces
     */
    void compactCars();

    /**
     * Print all {@link ParkingSpace} including empty spaces
     */
    void printParkingSpaces();

}