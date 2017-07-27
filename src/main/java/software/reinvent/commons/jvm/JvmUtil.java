package software.reinvent.commons.jvm;

import com.google.common.base.Preconditions;
import humanize.Humanize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Random;


/**
 * Created on 11.01.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
public class JvmUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JvmUtil.class);

    public static String getPid() {
        return ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
    }


    public static String getMachineName() {
        return ManagementFactory.getRuntimeMXBean().getName().split("@")[1];
    }


    public static long getUptime() {
        return ManagementFactory.getRuntimeMXBean().getUptime();
    }


    public static TerminateBuilder terminate() {
        return new TerminateBuilder();
    }

    public static void sleep(Duration time) {
        try {
            LOGGER.info("Will sleep {}.", time);
            Thread.sleep(time.toMillis());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sleep(int minSecond, int maxSeconds) {
        sleep(minSecond, maxSeconds, ChronoUnit.SECONDS);
    }

    public static void sleep(int min, int max, TemporalUnit unit) {
        Preconditions.checkArgument(min >= max, "max must be greater than min");
        sleep(Duration.of(new Random().nextInt((max - min) + 1) + min, unit));
    }

    /**
     * Builder to terminate the JVM
     */
    public static class TerminateBuilder {

        private int builderReturnCode;
        private boolean builderThreaded = false;
        private String builderMessage;


        /**
         * Sets the exit value, default is 0 (zero).
         */
        public TerminateBuilder returnCode(int returnCode) {
            builderReturnCode = returnCode;
            return this;
        }


        /**
         * Starts the termination in a separate thread or blocking, default is true.
         */
        public TerminateBuilder threaded(boolean threaded) {
            builderThreaded = threaded;
            return this;
        }


        /**
         * Adds an optional user-defined message to the termination log.
         */
        public TerminateBuilder message(String message) {
            builderMessage = message;
            return this;
        }


        /**
         * Stops the JVM in the specified time
         */
        public void in(Duration time) {
            shutdown(time);
        }


        /**
         * Stops the JVM immediately
         */
        public void now() {
            shutdown(null);
        }


        protected void shutdown(Duration time) {
            if (builderThreaded) {
                Thread thread = new Thread(() -> shutdownFinal(time), "JvmTermination-thread");
                thread.setDaemon(false);
                thread.start();
            } else {
                shutdownFinal(time);
            }
        }


        protected void shutdownFinal(Duration time) {
            if (time == null) {
                LOGGER.info("The JavaVM will exit now, return code will be {" + builderReturnCode + "}");
            } else {
                LOGGER.info("The JavaVM will exit in {"
                        + Humanize.nanoTime(time.toNanos())
                        + "}, return code will be {"
                        + builderReturnCode
                        + "}");
                sleep(time);
            }
            System.exit(builderReturnCode);
        }
    }
}