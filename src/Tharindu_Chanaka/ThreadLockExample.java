package Tharindu_Chanaka;

import util.ResourceContentionDetector;

public class ThreadLockExample {

    public static void runExample() {
        // Create and simulate thread lock contention
        Thread thread1 = new Thread(() -> {
            String lockName = "sharedLock";
            ResourceContentionDetector.simulateContention(Thread.currentThread().getName(), lockName);
        });

        Thread thread2 = new Thread(() -> {
            String lockName = "sharedLock";
            ResourceContentionDetector.simulateContention(Thread.currentThread().getName(), lockName);
        });

        // Start threads
        thread1.start();
        thread2.start();

        // Wait for threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Monitor lock contention
        ResourceContentionDetector.monitorLockContention(thread1.getName(), "sharedLock");
        ResourceContentionDetector.monitorLockContention(thread2.getName(), "sharedLock");
    }
}
