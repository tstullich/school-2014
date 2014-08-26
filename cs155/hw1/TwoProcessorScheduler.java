import java.util.Arrays;
import java.util.Random;

/**
 * Provides exact and greedy approximate solution algorithms for the Two Processor Scheduling problem.
 * The greedy algorithms work by considering the tasks in some order and assigning the next task
 * to the processor with the smallest load so far.
 * @author Jeff Smith
 * @version for CS 155, Fall 2014, SJSU
 */
public class TwoProcessorScheduler {
    private int[] weight;                      // the sequence of task lengths
    public int n;                              // number of tasks
    public static final int MAX_LENGTH = 100;  // maximum length of a task

    /**
     * Randomly constructs an instance of the Two Processor Scheduling problem
     * where the task times are chosen uniformly and randomly from 1 through
     * MAX_LENGTH
     * @param n the number of tasks
     * @param r a random object for constructing the task times
     * @param seed a seed for the random object, to guarantee repeatability
     */
    public TwoProcessorScheduler(int n, Random r, int seed)  {
        this.n = n;
        weight = new int[n];
        r = new Random(seed);
        for (int i = 0; i < n; i++) {
            weight[i] = 1 + r.nextInt(MAX_LENGTH);
        }
    }

    /**
     * Constructs an instance of the Two Processor Scheduling problem
     * with a given
     * @param w the sequence of task times, which is not copied
     * @param r a random object for constructing the task times
     * @param seed a seed for the random object, to guarantee repeatability
     */
    public TwoProcessorScheduler(int[] w) {
        this.n = w.length;
        weight = w;
    }

    /**
     * Constructs an exact solution to the Two Processor Scheduling problem
     * by calling a helper method that considers both an assignment to processor 1
     * and procesor 2, continuing both solutions are recursively, and returning
     * the better of the two final solutions.
     * @return the resulting schedule
     */
    public Schedule exactBacktracking() {
        boolean[] result = new boolean[n];
        return exactRecursive(n, 0, 0, result);
    }

    /**
     * Continues a partial solution to the Two Processor Scheduling problem by
     * considering both an assignment to processor 1
     * and procesor 2, continuing both solutions are recursively, and returning
     * the better of the two final solutions.
     * @param n number of tasks remaining to be considered
     * @param total1 total time taken so for by processor 1
     * @param total2 total time taken so for by processor 2
     * @param result the partial schedule, in the format used by the Schedule class
     * @return the resulting schedule
     */
    private Schedule exactRecursive(int n, int total1, int total2, boolean[] result)  {
        if (n == 0) {
            return new Schedule(result, total1, total2);
        }
        else {
            result[n-1] = true;
            Schedule ifYes = exactRecursive(n - 1, total1 + weight[n - 1], total2, result);
            result[n-1] = false;
            Schedule ifNo = exactRecursive(n - 1, total1, total2 + weight[n - 1], result);
            int error1 = Math.abs(ifYes.getTotalTime1() - ifYes.getTotalTime2());
            int error2 = Math.abs(ifNo.getTotalTime1() - ifNo.getTotalTime2());

            return error1 <= error2 ? ifYes : ifNo;
        }
    }

    /**
     * Calculates something
     */
   public Schedule firstFit() {
      return null;
   }

   /**
    * Calculates something else
    */
   public Schedule firstFitDecreasing() {
      return null;
   }
}
