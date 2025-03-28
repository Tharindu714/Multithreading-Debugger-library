package util;

import java.util.logging.*;

public class CustomFormatter extends Formatter {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";    // SEVERE (Deadlocks)
    private static final String GREEN = "\u001B[32m";  // INFO (No Deadlocks, Active Threads)
    private static final String YELLOW = "\u001B[33m"; // WARNING (Blocked Threads)
    private static final String BLUE = "\u001B[34m";   // FINE (Waiting Threads)

    @Override
    public String format(LogRecord record) {
        String color;

        if (record.getLevel() == Level.SEVERE) {
            color = RED;
        } else if (record.getLevel() == Level.INFO) {
            color = GREEN;
        } else if (record.getLevel() == Level.WARNING) {
            color = YELLOW;
        } else if (record.getLevel() == Level.FINE) {
            color = BLUE;
        } else {
            color = RESET;
        }

        return color + record.getLevel() + ": " + formatMessage(record) + RESET + "\n";
    }
}

