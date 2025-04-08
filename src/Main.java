// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

import java.util.Scanner;

public class Main {
    public static final int parseIntErrorCode = -1111; // stores the error code returned when parsing a string as an integer doesn't work
    public static boolean debuggingEnabled = false;

    // a method to print debug messages to the console
    public static void debugPrint(String debugMessage) {
        // if debugging is not enabled, then exit the function
        if (!debuggingEnabled) return;
        System.out.println("\u001B[32m" + "[DEBUG]: " + "\u001B[0m" + debugMessage + "\n");
    }

    // a method to check whether the string provided can be parsed into an int or not
    public static boolean canParseAsInteger(String str) {
        try {
            Integer.parseInt(str); // attempts to parse the string as an integer
            return true; // if the parse succeeds, then return true
        } catch (Exception e) {
            debugPrint("Failed to parse '" + str + "' as an integer: " + e.getMessage()); // tells the user what went wrong if debugging is enabled
            return false; // the string couldn't be parsed as an integer, return false
        }
    }

    // a method that parses a sting into an int and returns it
    public static int parseAsInteger(String str) {
        try {
            return Integer.parseInt(str); // attempts to parse the string as an integer and returns the value of the integer parsed
        } catch (Exception e) {
            debugPrint("Failed to parse '" + str + "' as an integer: " + e.getMessage()); // tells the user what went wrong if debugging is enabled
            return parseIntErrorCode; // return the parseIntErrorCode
        }
    }

    public static void main(String[] args) {
        // checks if the length of the arguments provided is greater than 0
        if (args.length > 0) {
            // checks if the first argument is '-d' and that debugging is not already enabled,
            // and if both conditions are true, then set debuggingEnabled to true
            if (args[0].equals("-d") && !debuggingEnabled) {
                debuggingEnabled = true;
                debugPrint("Debugging turned on!");
            }
        }

        Scanner inputScanner = new Scanner(System.in); // creates a new scanner object to scan user input

        int[] numberStack = new int[2]; // creates the stack that parsed integers will be stored and added on
        int numberStackIndex = 0; // stores the current number stack index

        while (true) {
            debugPrint("Getting user input...");

            System.out.println("\nEnter a number to be added (.stop to exit): ");

            String currentInput = inputScanner.nextLine(); // stores the next input given by the user

            debugPrint("Checking if the current input is the '.stop' keyword...");

            // checks if the input is the keyword '.stop', and if it is, then exit the loop
            if (currentInput.equals(".stop")) {
                debugPrint("Exiting the main loop...");
                break;
            }

            debugPrint("Checking if the current input can be parsed as an int...");

            // checks if the input can be parsed as an integer
            if (canParseAsInteger(currentInput)) {
                debugPrint("Adding " + currentInput + " to the number stack...");

                // check if the current numberStackIndex is less than the length of the numberStack - 1, and if it is,
                // then push the currentInput as an integer onto the numberStack
                if (numberStackIndex < (numberStack.length - 1)) {
                    numberStack[numberStackIndex] = parseAsInteger(currentInput);
                    numberStackIndex++;
                } else { // else reset the numberStackIndex and add the numberStack's first two numbers together
                    numberStack[numberStackIndex] = parseAsInteger(currentInput);
                    numberStackIndex = 0;
                    System.out.println("\nResult of adding " + numberStack[0] + " and " + numberStack[1] + " is " + (numberStack[0] + numberStack[1]) + "!\n");
                    debugPrint("Added " + numberStack[0] + " and " + numberStack[1] + " from the number stack!");
                }
            } else { // else tell the user to enter a real number
                System.out.println("\nEnter a number!");
                debugPrint("Input '" + currentInput + "' is not a number!");
            }

            debugPrint("Looping!");
        }
    }
}