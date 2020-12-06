package com.jpcodes;

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
     * Props to be initialized from application.props
     **/
    public static String DELIMITER;     // Delimiter for parsing inputs
    public static String PARK;          // Prefix for park action
    public static String UN_PARK;       // Prefix for un-park action
    public static String COMPACT;       // Prefix for compact action

    public static void main(String[] args) {

        // Initialize properties
        try {
            initializeProps();
        } catch (FileNotFoundException ex) {
            System.out.println("Properties file was not found.");
            return;
        } catch (IOException ex) {
            System.out.println("Error while reading properties file.");
            return;
        }

        // Prompt user
        System.out.println("Please provide comma delimited list of actions... Type exit to end.");

        // Set up a scanner for stdin and get input
        Scanner scanner = new Scanner(System.in);
        String actionInputs;

        while (scanner.hasNext()) {
            actionInputs = scanner.nextLine();

            if (actionInputs.trim().equals("exit")) {
                break;
            }

            // Split string to array
            String[] inputArray = actionInputs.split(DELIMITER);

            parseInputs(inputArray);
        }
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
                System.out.println("Parking");
            } else if (firstChar.equals(UN_PARK)) {
                System.out.println("un-Parking");
            } else if (firstChar.equals(COMPACT)) {
                System.out.println("Compacting");
            } else {
                System.out.println("An invalid prefix value was provided: " + firstChar);
                return;
            }

        }
    }

    /**
     * Initialize variables with properties parsed from props file
     *
     * @throws IOException Exception caught while loading props file
     */
    private static void initializeProps() throws IOException {
        propertyService.loadProps("application.properties");

        DELIMITER = propertyService.getProp("delimiter");
        PARK = propertyService.getProp("prefix.park");
        UN_PARK = propertyService.getProp("prefix.un-park");
        COMPACT = propertyService.getProp("prefix.compact");
    }
}
