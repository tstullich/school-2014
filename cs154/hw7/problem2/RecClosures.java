import java.util.function.*;

public class RecClosures {
    public static Predicate<Integer> union(Predicate<Integer> memA,
        Predicate<Integer> memB) {
        return (n) -> memA.test(n) || memB.test(n);
    }

    public static Predicate<Integer> intersect(Predicate<Integer> memA,
        Predicate<Integer> memB) {
        return (n) -> memA.test(n) && memB.test(n);
    }

    public static Predicate<Integer> diff(Predicate<Integer> memA,
        Predicate<Integer> memB) {
        return (n) -> !(memA.test(n) || memB.test(n));
    }

    public static Predicate<Integer> cross(Predicate<Integer> memA,
        Predicate<Integer> memB) {
        return (n) -> !(memA.test(n) && memB.test(n));
    }

    public static boolean isSmall(Integer n) {
        return n < 10;
    }

    public static boolean divBy5(Integer n) {
        return n % 5 == 0;
    }

    public static void main(String[] args) {
        System.out.println("Union Test");
        Predicate<Integer> unionMem = union(RecClosures::isSmall,
            RecClosures::divBy5);
        for (int i = 0; i <= 20; i++) {
            System.out.println("mem(" + i + ") = " + unionMem.test(i));
        }

        System.out.println("Intersect Test");
        Predicate<Integer> intersectMem = intersect(RecClosures::isSmall,
            RecClosures::divBy5);
        for (int i = 0; i <= 20; i++) {
            System.out.println("mem(" + i + ") = " + intersectMem.test(i));
        }

        System.out.println("Diff Test");
        Predicate<Integer> diffMem = diff(RecClosures::isSmall,
            RecClosures::divBy5);
        for (int i = 0; i <= 20; i++) {
            System.out.println("mem(" + i + ") = " + diffMem.test(i));
        }

        System.out.println("Cross Test");
        Predicate<Integer> crossMem = cross(RecClosures::isSmall,
            RecClosures::divBy5);
        for (int i = 0; i <= 20; i++) {
            System.out.println("mem(" + i + ") = " + crossMem.test(i));
        }
    }
}
