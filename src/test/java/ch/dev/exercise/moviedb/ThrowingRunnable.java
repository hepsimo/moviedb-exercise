package ch.dev.exercise.moviedb;

@FunctionalInterface
public interface ThrowingRunnable {
    void run() throws Exception;
}
