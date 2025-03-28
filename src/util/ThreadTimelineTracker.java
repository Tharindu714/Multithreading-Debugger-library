package util;

import java.util.*;
import java.util.logging.*;

public class ThreadTimelineTracker {

    private static final Logger logger = Logger.getLogger(ThreadTimelineTracker.class.getName());
    private static final Map<String, List<String>> threadEvents = new HashMap<>();

    // Track the start of a thread
    public static void logThreadStart(String threadName) {
        logThreadEvent(threadName, "started");
    }

    // Track when a thread is waiting for a lock
    public static void logThreadBlocked(String threadName, String lockName) {
        logThreadEvent(threadName, "waiting for lock: " + lockName);
    }

    // Track when a thread finishes execution
    public static void logThreadFinish(String threadName) {
        logThreadEvent(threadName, "finished execution");
    }

    // Helper method to log thread events with timestamps
    private static void logThreadEvent(String threadName, String event) {
        String timestamp = new Date().toString();
        String eventMessage = "[" + timestamp + "] - Thread " + threadName + " " + event;
        
        // Log the event to the logger
        logger.log(Level.INFO, eventMessage);

        // Store the event in the map for later display
        threadEvents.putIfAbsent(threadName, new ArrayList<>());
        threadEvents.get(threadName).add(eventMessage);
    }

    // Display the full timeline of thread events
    public static void displayTimeline() {
        threadEvents.forEach((threadName, events) -> {
            System.out.println("Thread: " + threadName);
            events.forEach(System.out::println);
            System.out.println(); // Add a separator between threads
        });
    }
}
