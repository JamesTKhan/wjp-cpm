package com.jpcodes;

import java.util.Arrays;
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

            // make sure we are getting the inputs.
            System.out.println(Arrays.toString(inputArray));
        }
    }
}
