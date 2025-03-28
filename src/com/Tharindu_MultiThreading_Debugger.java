package com;

import Tharindu_Chanaka.ThreadExample;
import Tharindu_Chanaka.ThreadLockExample;
import Tharindu_Chanaka.ThreadMonitorApp_Tharindu;

public class Tharindu_MultiThreading_Debugger {
  public static void main(String[] args) {
        try {
            // Run Thread Monitor Application in a separate thread
            Thread monitorThread = new Thread(() -> {
                System.out.println("Starting Thread Monitor Application...");
                ThreadMonitorApp_Tharindu.monitorThreads();
            });
            monitorThread.start();
            
            // Add a delay after starting the ThreadMonitorApp
            Thread.sleep(5000); // Sleep for 5 seconds
            
            // Run Thread Example concurrently
            Thread exampleThread = new Thread(() -> {
                System.out.println("Running Thread Example...");
                ThreadExample.runExample();
            });
            exampleThread.start();
            
            // Add a delay before running the next example
            Thread.sleep(5000); // Sleep for 5 seconds
            
            // Run Thread Lock Example concurrently
            Thread lockThread = new Thread(() -> {
                System.out.println("Running Thread Lock Example...");
                ThreadLockExample.runExample();
            });
            lockThread.start();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
