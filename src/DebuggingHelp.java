// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

public class DebuggingHelp {
    public static boolean debuggingEnabled = false;

    // a method to print debug messages to the console
    public static void debugPrint(String debugMessage) {
        // if debugging is not enabled, then exit the function
        if (!debuggingEnabled) return;
        System.out.println("\u001B[32m" + "[DEBUG]: " + "\u001B[0m" + debugMessage + "\n");
    }

    // a method to initialize debugging
    public static void initializeDebugging(String[] args) {
        // checks if the length of the arguments provided is greater than 0
        if (args.length > 0) {
            // checks if the first argument is '-d' and that debugging is not already enabled,
            // and if both conditions are true, then set debuggingEnabled to true
            if (args[0].equals("-d") && !debuggingEnabled) {
                debuggingEnabled = true;
                debugPrint("Debugging turned on!");
            }
        }
    }
}
