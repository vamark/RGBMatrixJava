package util;

public class SleepUtil {
    private static final boolean SKIP_SLEEP = Boolean.parseBoolean(System.getProperty("skipSleep", "false"));

    @SuppressWarnings("squid:S2925")
    public static void sleepFor(long millis) {
        if (!SKIP_SLEEP) {
            try {
                Thread.sleep(millis);
            }
            catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
