package util;

import util.DeadlockDetector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DeadlockMonitor {

    public static void startMonitoring() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(DeadlockDetector::detectDeadlocks, 0, 5, TimeUnit.SECONDS);
    }
}