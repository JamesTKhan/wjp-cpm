package com.jpcodes;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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
            String[] inputArray = actionInputs.split(",");

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
            switch (firstChar) {
                case "p":
                    System.out.println("Parking");
                    break;
                case "u":
                    System.out.println("un-Parking");
                    break;
                case "c":
                    System.out.println("Compacting");
                    break;
                default:
                    System.out.println("Unknown value");
            }
        }
    }
}
