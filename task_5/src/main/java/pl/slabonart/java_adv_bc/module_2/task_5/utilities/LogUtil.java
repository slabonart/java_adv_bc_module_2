package pl.slabonart.java_adv_bc.module_2.task_5.utilities;


import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtil {

    private static final Logger LOGGER = Logger.getLogger(LogUtil.class.getName());

    public static synchronized void log(String message) {
        LOGGER.log(Level.INFO, message);
    }

    public static synchronized void error(String message) {
        LOGGER.log(Level.ALL, message);
    }
}
