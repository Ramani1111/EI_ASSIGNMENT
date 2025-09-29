package singleton;

import java.util.logging.*;

// Singleton pattern: simple LoggerManager ensuring one logger instance.
public class Main {
    public static void main(String[] args) {
        LoggerManager lm1 = LoggerManager.getInstance();
        LoggerManager lm2 = LoggerManager.getInstance();
        System.out.println("Same instance? " + (lm1 == lm2));
        lm1.getLogger().info("This is an info from singleton logger.");
    }
}

class LoggerManager {
    private static volatile LoggerManager instance;
    private final Logger logger;
    private LoggerManager() {
        logger = Logger.getLogger("AppLogger");
        logger.setLevel(Level.INFO);
    }
    public static LoggerManager getInstance() {
        if (instance == null) {
            synchronized(LoggerManager.class) {
                if (instance == null) instance = new LoggerManager();
            }
        }
        return instance;
    }
    public Logger getLogger() { return logger; }
}
