import java.util.Arrays;
import java.util.Random;

/**
 * Tests exact and approximate solution algorithms for the Two Processor Scheduling problem.
 * @author Jeff Smith
 * @version for CS 155, Fall 2014, SJSU
 */
public class A1 {
    public static Random r = new Random(); // for generating random instances
    public static void main(String[] args) {

        // random tests of the three algorithms
        for (int i = 1; i <= 3; i++) {
            TwoProcessorScheduler s = new TwoProcessorScheduler(10, r, i);
            System.out.println(s.firstFit());
            System.out.println(s.firstFitDecreasing());
            System.out.println(s.exactBacktracking());
            System.out.println();
        }

        // random tests of Decreasing version against the Exact solution
        // showing the ratio of the total times required
        for (int i = 1; i <= 100; i++) {
            TwoProcessorScheduler s = new TwoProcessorScheduler(20, r, 10 + i);
            Schedule sBad = s.firstFitDecreasing();
            Schedule sGood = s.exactBacktracking();
            System.out.println(((double) sBad.getTotalTime()) / sGood.getTotalTime());
        }

        System.out.println();
        // this case gives the worst-case ratio
        int[] w = {2, 2, 2, 3, 3};
        TwoProcessorScheduler s = new TwoProcessorScheduler(w);
        System.out.println(s.firstFit());
        Schedule sBad = s.firstFitDecreasing();
        Schedule sGood = s.exactBacktracking();
        System.out.println(sBad);
        System.out.println(sGood);
        System.out.println(((double) sBad.getTotalTime()) / sGood.getTotalTime());
    }
}
