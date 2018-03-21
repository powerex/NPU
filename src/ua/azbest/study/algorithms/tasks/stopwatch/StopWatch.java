package ua.azbest.study.algorithms.tasks.stopwatch;

/**
 * Created by AZbest on 18.09.2015.
 */
public class StopWatch {

    protected final long start;

    public StopWatch() {
        start = System.currentTimeMillis();
    }

    public double elapsedTime()
    {
        long now = System.currentTimeMillis();
        return (now - start)/1000.0;
    }
}
