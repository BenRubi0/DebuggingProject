// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

public class Utils {
    public static final int parseIntErrorCode = -1111; // stores the error code returned when parsing a string as an integer doesn't work

    // a method to check whether the string provided can be parsed into an int or not
    public static boolean canParseAsInteger(String str) {
        try {
            Integer.parseInt(str); // attempts to parse the string as an integer
            return true; // if the parse succeeds, then return true
        } catch (Exception e) {
            DebuggingHelp.debugPrint("Failed to parse '" + str + "' as an integer: " + e.getMessage()); // tells the user what went wrong if debugging is enabled
            return false; // the string couldn't be parsed as an integer, return false
        }
    }

    // a method that parses a sting into an int and returns it
    public static int parseAsInteger(String str) {
        try {
            return Integer.parseInt(str); // attempts to parse the string as an integer and returns the value of the integer parsed
        } catch (Exception e) {
            DebuggingHelp.debugPrint("Failed to parse '" + str + "' as an integer: " + e.getMessage()); // tells the user what went wrong if debugging is enabled
            return parseIntErrorCode; // return the parseIntErrorCode
        }
    }
}
