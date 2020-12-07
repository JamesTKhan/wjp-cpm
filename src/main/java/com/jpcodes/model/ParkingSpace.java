package com.jpcodes.model;

/**
 * Basic POJO for parking spaces
 */
public class ParkingSpace {

    private final int ticketNumber;
    private final String tag;

    public ParkingSpace(int ticket, String tag) {
        this.ticketNumber = ticket;
        this.tag = tag;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getTag() {
        return tag;
    }

}