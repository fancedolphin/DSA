package CHC5223;

public class Logger {
    // Method to log 
    public static void log(String action, String memberName, String path) {
        System.out.println(action + " - " + memberName + " - Path: " + path);
    }

    // Method to log simple messages
    public static void log(String message) {
        System.out.println(message);
    }
}



