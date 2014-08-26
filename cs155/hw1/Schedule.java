import java.util.Arrays;

/**
 *  Represents a schedule of tasks for the Two Processor Scheduling problem
 *  @author Jeff Smith
 *  @version for CS 155, Fall 2014, SJSU
 */

public class Schedule {
    // the schedule, from first task to last
    // if entry i is true, then task i is scheduled for the first processor,
    // otherwise it's scheduled for the second processor

    private boolean[] isForFirstProcessor;

    // total times of the tasks scheduled for processors 1 and 2 respectively
    private int totalTime1 = 0;
    private int totalTime2 = 0;

    // Constructs an empty Schedule for n tasks
    public Schedule(int n) {
       isForFirstProcessor = new boolean[n];
       totalTime1 = 0;
       totalTime2 = 0;
    }

    /**
     * Constructs a Schedule with a given assignment of tasks
     * and total times used by both processors
     * @param b the assignment of tasks
     * @param t1 the total time of the tasks assigned to processor 1
     * @param t1 the total time of the tasks assigned to processor 2
     */
    public Schedule(boolean[] b, int t1, int t2) {
       isForFirstProcessor = Arrays.copyOf(b, b.length);
       totalTime1 = t1;
       totalTime2 = t2;
    }

    /**
     * Gets the total time of the tasks assigned to processor 1
     * @return the total time of the tasks assigned to processor 1
     */
    public int getTotalTime1() {
        return totalTime1;
    }

    /**
     * Gets the total time of the tasks assigned to processor 2
     * @return the total time of the tasks assigned to processor 2
     */
    public int getTotalTime2() {
        return totalTime2;
    }

    /**
     * Gets the schedule of tasks
     * @return a copy of the schedule of tasks
     */
    public boolean[] getSked() {
        return Arrays.copyOf(isForFirstProcessor, isForFirstProcessor.length);
    }

    /**
     * Finds the time required by the schedule
     * @return the larger of the time required by processor 1 and the time required by processor 2
     */
    public int getTotalTime() {
        return Math.max(totalTime1, totalTime2);
    }

    /**
     * Gets a string version of the schedule
     * @return a string version of the schedule
     */
    public String toString() {
        return Arrays.toString(isForFirstProcessor) + " " + totalTime1 + ":" + totalTime2;
    }
}
