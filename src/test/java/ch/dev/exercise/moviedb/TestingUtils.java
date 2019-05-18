package ch.dev.exercise.moviedb;

import javax.annotation.Nullable;
import org.junit.Assert;
import org.springframework.boot.CommandLineRunner;

public class TestingUtils {

    /**
     * Handles exceptions thrown from a runnable
     *
     * @param runnable The runnable to run that will throw the exception
     * @param ex       Expected exception
     * @param message  Expected exception message. Can be null if message is not important.
     */
    public static void expectException(CommandLineRunner runnable, Class<? extends Exception> ex,
        @Nullable String message) throws Exception {
        try {
            runnable.run();
            Assert.fail("Test did not throw expected " + ex.getSimpleName() + " exception.");
        }
        catch (Exception e) {
            if (e.getClass().isAssignableFrom(ex)) {
                if (message != null) {
                    Assert.assertEquals(e.getMessage(), message);
                }
            }
            else {
                throw e;
            }
        }
    }

    /**
     * Handles exceptions thrown from a runnable
     *
     * @param runnable The runnable to run that will throw the exception
     * @param ex       Expected exception
     */
    public static void expectException(CommandLineRunner runnable,
        Class<? extends Exception> ex) throws Exception {
        TestingUtils.expectException(runnable, ex, null);
    }
}

