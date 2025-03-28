# **Multithreading Debugging Library**  
🚀 **Version 1.0.0** | 📅 **March 28, 2025**  

## **📌 Overview**  
The **Multithreading Debugging Library** helps developers detect and debug **deadlocks, race conditions, and thread execution anomalies** in Java applications.  

### ✅ **Features**  
- **Deadlock Detection** 🔒  
- **Race Condition Monitoring** ⚠  
- **Thread Execution Tracking** 📊  
- **Real-Time Logging & Alerts**  

---

## **📥 Installation**  
Click to expand installation steps

🔹 Step 1: Download the JAR File
Download the latest release from the GitHub Releases page.
Use this Below Link
**https://github.com/Tharindu714/Multithreading-Debugger-library/releases/tag/Multithreading-DebuggerV01**

🔹 Step 2: Add JAR to Your Project
Place the JAR file inside your project's lib/ folder.
Use this Below Link
**https://github.com/Tharindu714/Multithreading-Debugger-library/blob/5dd106b4a6046cce49c309e0b79d48474995ed1f/lib/MultithreadingDebugger.jar**

🔹 Step 3: Configure Your Classpath
Add the JAR file to your Java build path (in Eclipse, IntelliJ, or NetBeans).

## **🚀 Usage Example**  
### **Monitor Threads & Detect Deadlocks**  

```java
import debugging.ThreadMonitorApp;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Thread Monitor...");
        ThreadMonitorApp.monitorThreads();
    }
}
```

### **Detect Race Conditions**  
```java
import debugging.RaceConditionDetector;

public class TestRaceCondition {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> RaceConditionDetector.detectRaceCondition(Thread.currentThread()));
        Thread thread2 = new Thread(() -> RaceConditionDetector.detectRaceCondition(Thread.currentThread()));

        thread1.start();
        thread2.start();
    }
}
```

---

## **📜 Changelog**  

| Version | Changes |
|---------|---------|
| **1.0.0** | Initial release with deadlock and race condition detection |
| **1.1.0** *(Upcoming)* | Thread Visualization Dashboard |

---

## **📢 Contribute & Support**  
- 📌 **GitHub Repository:** https://github.com/Tharindu714/Multithreading-Debugger-library.git
- 📌 **Documentation:** https://github.com/Tharindu714/Multithreading-Debugger-library/blob/main/README.md 
- 🐛 Found a bug? Report it : Open an issue on **GitHub**.

🚀 **Happy Debugging!** 🔥  

## **Library doesn't directly enhance performance, it indirectly leads to performance improvements by helping developers resolve concurrency issues that can cause slowdowns, crashes, or unexpected behaviors**

---
