package Tharindu_Chanaka;

import util.DeadlockDetector;
import util.RaceConditionDetector;

public class ThreadMonitorApp_Tharindu {

    public static void monitorThreads() {
        // Start monitoring threads
        DeadlockDetector.monitorThreadStates();

        // Check for deadlocks every 5 seconds
        Thread deadlockThread = new Thread(() -> {
            while (true) {
                DeadlockDetector.detectDeadlocks();
                try {
                    Thread.sleep(5000); // Wait for 5 seconds before checking again
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start the deadlock detection in a separate thread
        deadlockThread.start();

        // Simulate race condition detection
        Thread thread1 = new Thread(() -> {
            while (true) {
                RaceConditionDetector.detectRaceCondition(Thread.currentThread());
            }
        });
        
        Thread thread2 = new Thread(() -> {
            while (true) {
                RaceConditionDetector.detectRaceCondition(Thread.currentThread());
            }
        });

        // Start both threads for race condition detection
        thread1.start();
        thread2.start();
    }
}

