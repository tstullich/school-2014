import java.util.*;
import java.util.function.*;

public class Tester {

public static void main(String args[]) {
    Set<Integer> a = new HashSet<Integer>();
 
    // a = first 20 multiples of 3
    for(int i = 0; i < 20; i++) {
        a.add(i * 3);
    }
    Set<Integer> b = Operations.filter(a, (Integer x) -> x%2 == 0);
    Set<Integer> c = Operations.map(b, (x) -> 2 * x);
    System.out.println("a = " + a);
    System.out.println("b = " + b);
    System.out.println("c = " + c);
    }
}
