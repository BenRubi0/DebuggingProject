// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DebuggingHelp.initializeDebugging(args); // initializes the debug helper

        Scanner inputScanner = new Scanner(System.in); // creates a new scanner object to scan user input

        int[] numberStack = new int[2]; // creates the stack that parsed integers will be stored and added on
        int numberStackIndex = 0; // stores the current number stack index

        while (true) {
            DebuggingHelp.debugPrint("Getting user input...");

            System.out.println("\nEnter a number to be added (.stop to exit): ");

            String currentInput = inputScanner.nextLine(); // stores the next input given by the user

            DebuggingHelp.debugPrint("Checking if the current input is the '.stop' keyword...");

            // checks if the input is the keyword '.stop', and if it is, then exit the loop
            if (currentInput.equals(".stop")) {
                DebuggingHelp.debugPrint("Exiting the main loop...");
                break;
            }

            DebuggingHelp.debugPrint("Checking if the current input can be parsed as an int...");

            // checks if the input can be parsed as an integer
            if (Utils.canParseAsInteger(currentInput)) {
                DebuggingHelp.debugPrint("Adding " + currentInput + " to the number stack...");

                // check if the current numberStackIndex is less than the length of the numberStack - 1, and if it is,
                // then push the currentInput as an integer onto the numberStack
                if (numberStackIndex < (numberStack.length - 1)) {
                    numberStack[numberStackIndex] = Utils.parseAsInteger(currentInput);
                    numberStackIndex++;
                } else { // else reset the numberStackIndex and add the numberStack's first two numbers together
                    numberStack[numberStackIndex] = Utils.parseAsInteger(currentInput);
                    numberStackIndex = 0;
                    System.out.println("\nResult of adding " + numberStack[0] + " and " + numberStack[1] + " is " + (numberStack[0] + numberStack[1]) + "!\n");
                    DebuggingHelp.debugPrint("Added " + numberStack[0] + " and " + numberStack[1] + " from the number stack!");
                }
            } else { // else tell the user to enter a real number
                System.out.println("\nEnter a number!");
                DebuggingHelp.debugPrint("Input '" + currentInput + "' is not a number!");
            }

            DebuggingHelp.debugPrint("Looping!");
        }

        DebuggingHelp.debugPrint("Running the string tokenizer project...");

        StringTokenizerProject.run();
    }
}