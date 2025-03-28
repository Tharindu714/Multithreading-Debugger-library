package util;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.logging.*;

public class DeadlockDetector {

    private static final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    private static final Logger logger = Logger.getLogger(DeadlockDetector.class.getName());

    static {
        // Set up custom logger formatting with colors
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new CustomFormatter());
        logger.setUseParentHandlers(false);
        logger.addHandler(consoleHandler);
    }

    public static void detectDeadlocks() {
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();

        if (deadlockedThreads != null && deadlockedThreads.length > 0) {
            logger.log(Level.SEVERE, "⚠ Deadlock detected! Affected threads:");
            ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(deadlockedThreads);

            Arrays.stream(threadInfos).forEach(threadInfo
                    -> logger.log(Level.SEVERE, "Thread: {0} is waiting for lock: {1}",
                            new Object[]{threadInfo.getThreadName(), threadInfo.getLockName()}
                    )
            );
        } else {
            logger.log(Level.INFO, "✅ No deadlocks detected.");
        }
    }

    public static void monitorThreadStates() {
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

        logger.log(Level.INFO, "📊 Thread Lifecycle Monitoring:");

        for (ThreadInfo threadInfo : threadInfos) {
            String threadName = threadInfo.getThreadName();
            Thread.State state = threadInfo.getThreadState();

            if (state == Thread.State.BLOCKED) {
                logger.log(Level.WARNING, "⛔ BLOCKED → {0}", threadName);
            } else if (state == Thread.State.WAITING || state == Thread.State.TIMED_WAITING) {
                logger.log(Level.FINE, "⏳ WAITING → {0}", threadName);
            } else if (state == Thread.State.RUNNABLE) {
                logger.log(Level.INFO, "🏃 ACTIVE → {0}", threadName);
            }
        }
    }
}
