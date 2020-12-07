package com.jpcodes;

import com.jpcodes.message.ErrorMessageEnum;
import com.jpcodes.service.ParkingService;
import com.jpcodes.service.ParkingServiceImpl;
import com.jpcodes.service.PropertyService;
import com.jpcodes.service.PropertyServiceImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    /**
     * PropertyService for loading and retrieving props
     **/
    public static PropertyService propertyService = new PropertyServiceImpl();

    /**
     * ParkingService for handling park actions
     **/
    public static ParkingService parkingService;

    /**
     * User input that should stop the program
     */
    public static final String EXIT_CMD = "exit";

    /**
     * Props to be initialized from application.props
     **/
    private static int MAX_SPACES;      // Max spaces the garage can hold
    public static int TICKET_START;     // Ticket start count
    public static String DELIMITER;     // Delimiter for parsing inputs
    public static String PARK;          // Prefix for park action
    public static String UN_PARK;       // Prefix for un-park action
    public static String COMPACT;       // Prefix for compact action

    public static void main(String[] args) {

        // Initialize properties
        try {
            initializeProps();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            return;
        } catch (IOException ex) {
            System.out.println(ErrorMessageEnum.ERR_READ_FILE.getDescription());
            return;
        }

        // Initialize our parking service with configs
        parkingService = new ParkingServiceImpl(MAX_SPACES, DELIMITER, TICKET_START);

        // Prompt user
        System.out.println("Please provide comma delimited list of actions... Type exit to end.");

        // Set up a scanner for stdin and get input
        Scanner scanner = new Scanner(System.in);
        String actionInputs;

        // Start main application loop
        while (scanner.hasNext()) {
            actionInputs = scanner.nextLine();

            if (actionInputs.trim().equalsIgnoreCase(EXIT_CMD)) {
                break;
            }

            // Split string to array
            String[] inputArray = actionInputs.split(DELIMITER);

            parseInputs(inputArray);
        }

        // Close out the scanner
        scanner.close();
    }

    /**
     * Process each input command via switch statement
     *
     * @param inputs stdin to parse
     */
    private static void parseInputs(String[] inputs) {
        // For each given input...
        for (String input : inputs) {

            input = input.trim();

            // Get first character off the string
            String firstChar = input.substring(0, 1).toLowerCase();

            // Handle accordingly
            if (firstChar.equals(PARK)) {
                try {
                    parkingService.parkCar(input.substring(1));
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                    // Error parking car, likely there was no room, lets print out current occupancy
                    System.out.print("Current Occupancy: ");
                    parkingService.printParkingSpaces();
                    return;
                }
            } else if (firstChar.equals(UN_PARK)) {
                // Parse ticketNumber
                int ticketNumber;
                String ticketString = input.substring(1);

                try {
                    ticketNumber = Integer.parseInt(ticketString);
                } catch (NumberFormatException ex) {
                    System.out.println(ErrorMessageEnum.TICKET_NOT_INTEGER.getDescription(ticketString));
                    System.out.println("Original command: " + input);
                    return;
                }

                parkingService.unParkCar(ticketNumber);
            } else if (firstChar.equals(COMPACT)) {
                parkingService.compactCars();
            } else {
                System.out.println(ErrorMessageEnum.INVALID_PREFIX.getDescription(firstChar));
                return;
            }

        }

        parkingService.printParkingSpaces();
    }

    /**
     * Initialize variables with properties parsed from props file
     *
     * @throws IOException Exception caught while loading props file
     */
    private static void initializeProps() throws IOException {
        propertyService.loadProps("application.properties");

        MAX_SPACES = Integer.parseInt(propertyService.getProp("max-spaces"));
        TICKET_START = Integer.parseInt(propertyService.getProp("ticket-start"));
        DELIMITER = propertyService.getProp("delimiter");
        PARK = propertyService.getProp("prefix.park");
        UN_PARK = propertyService.getProp("prefix.un-park");
        COMPACT = propertyService.getProp("prefix.compact");
    }
}