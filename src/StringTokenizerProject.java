// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us
// 2/13/2025

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StringTokenizerProject {
    // creates a method to easily get tokens in csv formatted strings
    public static String[] getCSVTokens(String csvString) {
        StringTokenizer tokenizer = new StringTokenizer(csvString, ", \t"); // creates a new string tokenizer for the string provided

        String[] tokens = new String[tokenizer.countTokens()]; // creates an array of strings that will contain all the tokens from the tokenizer
        int tokenIndex = 0; // creates a variable to store the current token index

        // loop to set index values
        while (tokenizer.hasMoreTokens()) {
            tokens[tokenIndex] = tokenizer.nextToken(); // sets the current tokenIndex to the tokenizers next token
            tokenIndex += 1; // adds one to the current tokenIndex
        }

        return tokens; // returns the array of all the tokens
    }

    // creates a method to easily find if a string is a float
    public static float getFloat(String str) {
        // try-catch to catch exceptions
        try {
            return Float.parseFloat(str); // returns the string as a float
        } catch (Exception e) {
            return -11.1f; // if the method parseFloat() failed, then return the failed float
        }
    }

    // creates a method to easily create a file writer object
    public static FileWriter createNewFileWriter(File file) {
        try {
            return new FileWriter(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void run() {
        File fileToRead = new File("letter_frequency.csv"); // creates the fileToRead variable
        Scanner fileReader = null; // creates the fileReader variable

        DebuggingHelp.debugPrint("Attempting to create initialize the file scanner...");

        // try-catch to catch an exception
        try {
            fileReader = new Scanner(fileToRead); // initializes the fileReader variable
            DebuggingHelp.debugPrint("Initialized the file scanner!");
        } catch (Exception e) {
            System.out.println(e.getMessage()); // tells the user what went wrong
            DebuggingHelp.debugPrint("Failed to create the file scanner: " + e.getMessage());
        }

        int allFrequenciesAdded = 0; // creates and initializes a variable to store all the frequencies added up
        float allPercentagesAdded = 0.0f; // creates and initializes a variable to store all the percentages added up

        DebuggingHelp.debugPrint("Attempting to read file...");

        // a loop to read line while the file still has more
        while (fileReader.hasNext()) {
            String next = fileReader.nextLine();

            System.out.println("\n"); // creates a new line
            String[] lineTokens = getCSVTokens(next); // gets the csv tokens for the current line

            DebuggingHelp.debugPrint("Looping through each token in: " + next);

            // goes through all the tokens for the current line
            for (String str : lineTokens) {
                // checks if the current token can be converted into an int
                if (Utils.canParseAsInteger(str)) {
                    System.out.println("\t\t\tFrequency: " + Utils.parseAsInteger(str)); // tells the user the frequency as a number
                    allFrequenciesAdded += Utils.parseAsInteger(str); // adds to the allFrequenciesAdded variable
                    DebuggingHelp.debugPrint("Parsed " + str + " as an int!");
                    // checks if the current token can be converted into a float
                } else if (getFloat(str) != -11.1f) {
                    System.out.println("\t\t\tPercentage: " + getFloat(str));// tells the user the percentage as a float
                    allPercentagesAdded += getFloat(str); // adds the percentage to the total of percentages
                    DebuggingHelp.debugPrint("Parsed " + str + " as a float!");
                    // if the current token is not an int or a float, print it out as a string
                } else {
                    System.out.println("\t\t"+str); // prints out the current token
                    DebuggingHelp.debugPrint("Couldn't parse " + str + " to a float or an int!");
                }
            }

            DebuggingHelp.debugPrint("Looping!");
        }

        DebuggingHelp.debugPrint("Finished adding up values!");

        System.out.println("\n\tTotal of percentages: "+allPercentagesAdded+"%"); // tells the user all the percentages added
        System.out.println("\tAll frequencies added: "+allFrequenciesAdded); // tells the user all the frequencies added
        System.out.println("\tThe average of all frequencies: "+(allFrequenciesAdded/2)+"\n"); // tells the user all the frequencies averaged

        // user data

        int maxUsers = 5; // creates a variable to store the max amount of users
        int currentUser = 1; // creates a variable to store the current user
        int dataLots = 5; // creates a variable for the amount of each users max lot size of data
        int userDataBufferSize = (dataLots*maxUsers)+20; // creates a variable to store the max amount of data to be stored in the user data string

        String[] userData = new String[userDataBufferSize]; // creates a string to store each user's data

        File userDataFile = new File("user_data.txt"); // creates the file we're going to use to store user data
        FileWriter userDataWriter = createNewFileWriter(userDataFile);

        Scanner userInputScanner = new Scanner(System.in); // creates a new scanner object for user input

        DebuggingHelp.debugPrint("Attempting to gather 5 users information...");

        // keeps running while not all user data has been stored
        while (currentUser <= maxUsers) {
            System.out.printf("\nEnter data for user %d (name, address, zipcode, phone number, birthday): ", currentUser); // tells the user to enter data

            StringTokenizer userInputTokenizer = new StringTokenizer(userInputScanner.nextLine(), ",;"); // creates a string tokenizer for the users input
            String[] userDataTokens = new String[userInputTokenizer.countTokens()]; // creates an array of strings that will contain all the tokens from the users input
            String userDataString = ""; // creates a string to store the user data

            DebuggingHelp.debugPrint("Looping through each user data token gathered...");

            for (int tokenIndex = 0; tokenIndex < userDataTokens.length; tokenIndex++) {
                String currentDataValue = ""; // stores the current data type the user entered

                userDataTokens[tokenIndex] = userInputTokenizer.nextToken(); // sets the current token in the tokens array to the current next string tokenizer token

                DebuggingHelp.debugPrint("Current token index: " + tokenIndex);

                // checks if the current number matches the index for the data value
                if (tokenIndex == 0) { currentDataValue = "Name"; /* sets the current data value */ System.out.printf("\tName entered: %s", userDataTokens[tokenIndex]); /* tells the user what name the user input */ }
                else if (tokenIndex == 1) { currentDataValue = "Address"; /* sets the current data value */ System.out.printf("\t\tAddress entered: %s", userDataTokens[tokenIndex]); /* tells the user what address the user input */ }
                else if (tokenIndex == 2) { currentDataValue = "Zipcode"; /* sets the current data value */ System.out.printf("\t\tZipcode entered: %s", userDataTokens[tokenIndex]); /* tells the user what zipcode the user input */ }
                else if (tokenIndex == 3) { currentDataValue = "Phone number"; /* sets the current data value */ System.out.printf("\t\tPhone number entered: %s", userDataTokens[tokenIndex]); /* tells the user what phone number the user input */ }
                else if (tokenIndex == 4) { currentDataValue = "Birthday"; /* sets the current data value */ System.out.printf("\t\tBirthday entered: %s", userDataTokens[tokenIndex]); /* tells the user what birthday the user input */ }

                userDataString = userDataString.concat("\n\t" + currentDataValue + ": " + userDataTokens[tokenIndex]); // adds the current user token formatted to the user data string

                DebuggingHelp.debugPrint("Building the 'userDataString': " + userDataString);
            }

            DebuggingHelp.debugPrint("Finished building the 'userDataString': " + userDataString);

            userData[currentUser] = String.format("\nUser %d data:", currentUser) + userDataString; // creates a formatted version of the userDataString and adds it to an array

            DebuggingHelp.debugPrint("Closing the user data writer...");

            // try-catch to get exceptions
            try {
                // check if the userDataWriter is not null
                if (userDataWriter != null) {
                    userDataWriter.write("\n" + userData[currentUser]); // write the user data to the file
                }
            } catch (IOException e) {
                System.out.println(e.getMessage()); // tells the user what went wrong
                DebuggingHelp.debugPrint("Failed to close the user data writer: " + e.getMessage());
            }

            currentUser += 1; // adds one to the current user
        }

        DebuggingHelp.debugPrint("Closing the user data writer...");

        // try-catch to get exceptions
        try {
            // check if the userDataWriter is not null
            if (userDataWriter != null) {
                userDataWriter.close(); // close the file writer
            }
        } catch (Exception e) {
            System.out.println(e.getMessage()); // tells the user what went wrong
            DebuggingHelp.debugPrint("Failed to close the user data writer: " + e.getMessage());
        }

        DebuggingHelp.debugPrint("Closing the file reader...");

        // try-catch to get exceptions
        try {
            fileReader.close(); // closes the file reader scanner
            userInputScanner.close(); // closes the current user input scanner
        } catch (Exception e) {
            System.out.println(e.getMessage()); // tells the user what went wrong
            DebuggingHelp.debugPrint("Failed to close the file reader: " + e.getMessage());
        }
    }
}
