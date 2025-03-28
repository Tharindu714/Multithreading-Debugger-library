package Tharindu_Chanaka;

import util.ThreadTimelineTracker;

public class ThreadExample {
    public static void runExample() {
        // Create and start threads
        Thread thread1 = new Thread(() -> {
            ThreadTimelineTracker.logThreadStart(Thread.currentThread().getName());
            try {
                synchronized (ThreadExample.class) {
                    ThreadTimelineTracker.logThreadBlocked(Thread.currentThread().getName(), "ThreadExample.class");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadTimelineTracker.logThreadFinish(Thread.currentThread().getName());
        });

        Thread thread2 = new Thread(() -> {
            ThreadTimelineTracker.logThreadStart(Thread.currentThread().getName());
            try {
                synchronized (ThreadExample.class) {
                    ThreadTimelineTracker.logThreadBlocked(Thread.currentThread().getName(), "ThreadExample.class");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadTimelineTracker.logThreadFinish(Thread.currentThread().getName());
        });

        // Start the threads
        thread1.start();
        thread2.start();

        // Wait for threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display the thread execution timeline
        ThreadTimelineTracker.displayTimeline();
    }
}
