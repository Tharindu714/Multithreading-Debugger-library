package util;

import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.*;

public class ResourceContentionDetector {

    private static final Logger logger = Logger.getLogger(ResourceContentionDetector.class.getName());
    private static final Map<String, Long> threadLockTimes = new HashMap<>();
    private static final long TIME_THRESHOLD = 5000;  // Threshold in milliseconds for inefficient lock time
    private static final Lock monitorLock = new ReentrantLock();  // Lock for monitoring resources

    // Track when a thread acquires a lock
    public static void logLockAcquisition(String threadName, String lockName) {
        long timestamp = System.currentTimeMillis();
        logger.log(Level.INFO, "{0} acquired lock: {1} at {2}", 
                   new Object[]{threadName, lockName, timestamp});
        threadLockTimes.put(threadName, timestamp);  // Store timestamp when thread acquires the lock
    }

    // Track when a thread releases a lock
    public static void logLockRelease(String threadName, String lockName) {
        long timestamp = System.currentTimeMillis();
        long acquisitionTime = threadLockTimes.getOrDefault(threadName, timestamp);
        long waitTime = timestamp - acquisitionTime;
        
        logger.log(Level.INFO, "{0} released lock: {1} after {2}ms", 
                   new Object[]{threadName, lockName, waitTime});
        
        if (waitTime > TIME_THRESHOLD) {
            logger.log(Level.WARNING, "⚠ Inefficient lock wait detected! {0} waited for {1}ms to acquire lock: {2}", 
                       new Object[]{threadName, waitTime, lockName});
        }

        threadLockTimes.remove(threadName);  // Remove the thread from the waiting list once the lock is released
    }

    // Monitor lock contention between threads
    public static void monitorLockContention(String threadName, String lockName) {
        if (monitorLock.tryLock()) {
            try {
                long currentTime = System.currentTimeMillis();
                long lockAcquisitionTime = threadLockTimes.getOrDefault(threadName, currentTime);
                long waitTime = currentTime - lockAcquisitionTime;
                
                if (waitTime > TIME_THRESHOLD) {
                    logger.log(Level.WARNING, "⚠ Lock contention detected! Thread {0} is waiting too long to acquire lock: {1} ({2}ms)", 
                               new Object[]{threadName, lockName, waitTime});
                }
            } finally {
                monitorLock.unlock();  // Ensure the lock is released after checking
            }
        }
    }

    // A method to simulate the lock contention detection in real-time usage
    public static void simulateContention(String threadName, String lockName) {
        logLockAcquisition(threadName, lockName);
        
        // Simulate wait time (e.g., thread waiting for the lock)
        try {
            Thread.sleep(3000);  // Sleep for 3 seconds, representing waiting for lock
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logLockRelease(threadName, lockName);
    }
}
