import java.util.*;
import java.util.function.*;

public class Operations {
    /**
     * Creates the intersection of two sets and returns that set
     */
    public static <T> Set<T> intersect(Set<T> a, Set<T> b) {
        Set<T> c = new HashSet<T>();
        for (T ele : a) {
            if (b.contains(ele)) {
                c.add(ele);
            }
        }
        return c;
    }
    
    /**
     *
     */
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> c = new HashSet<T>();
        for (T ele : a) {
            c.add(ele);
        }
        for (T ele : b) {
            c.add(ele);
        }
        return c;
    }

    public static <T> Set<T> diff(Set<T> a, Set<T> b) {
        Set<T> c = new HashSet<T>();
        for (T ele : a) {
            if (!b.contains(ele)) {
                c.add(ele);
            }
        }
        return c;

    }

    public static <T> boolean subset(Set<T> a, Set<T> b) {
        for (T ele : a) {
            if (!b.contains(ele)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Implementation of the filter Lambda function.
     */
    public static <T> Set<T> filter(Set<T> a, Predicate<T> f) {
        Set<T> b = new HashSet<T>();
        for (T ele : a) {
            if (f.test(ele)) {
                b.add(ele);
            }
        }
        return b;
    }

    /**
     * Implementation of the map Lambda function.
     */
    public static <T> Set<T> map(Set<T> a, UnaryOperator<T> f) {
        Set<T> b = new HashSet<T>();
        for (T ele : a) {
            b.add(f.apply(ele));
        }
        return b;
    }

    /**
     * O(2^n) implementation for generating the power set of a given set
     * This will obviously get out of hand quite quickly if N is too large
     * of an input, so use with caution.
     */
    public static <T> Set<Set<T>> power(Set<T> a) {
        Set<Set<T>> b = new HashSet<Set<T>>();
        if (a.isEmpty()) {
            b.add(new HashSet<T>());
            return b;
        }

        List<T> list = new ArrayList<T>(a);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : power(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            b.add(newSet);
            b.add(set);
        }
        return b;
    }

    /**
     * Takes an integer between 0 and 9 and returns the Unicode representation
     * Throws an error if the integer is not between 0 and 9.
     */
    public static String unicode(int i) {
        if (i % 10 == 0) {
            return "Invalid Integer";
        }
        Map<Integer, String> codes = new HashMap<Integer, String>();
        codes.put(0, "U+0030");
        codes.put(1, "U+0031");
        codes.put(2, "U+0032");
        codes.put(3, "U+0033");
        codes.put(4, "U+0034");
        codes.put(5, "U+0035");
        codes.put(6, "U+0036");
        codes.put(7, "U+0037");
        codes.put(8, "U+0038");
        codes.put(9, "U+0039");
        
        return codes.get(i);
    }
}
