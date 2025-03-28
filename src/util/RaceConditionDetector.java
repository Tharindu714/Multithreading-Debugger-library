package util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RaceConditionDetector {

    private static final Lock lock = new ReentrantLock();
    private static int sharedResource = 0;
    private static boolean raceConditionDetected = false;

    public static void detectRaceCondition(Thread thread) {
        if (!raceConditionDetected) {
            if (lock.tryLock()) {
                try {
                    System.out.println(thread.getName() + " is accessing shared resource.");
                    sharedResource++;
                    Thread.sleep(100); // Simulate work with the shared resource
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("⚠ Race condition detected! " + thread.getName() + " tried to access shared resource at the same time.");
                raceConditionDetected = true;  // Flag to prevent repeated detection
            }
        }
    }

    public static void resetRaceConditionDetection() {
        raceConditionDetected = false;  // Method to reset the flag if needed
    }
}

