import java.util.*;
import java.util.function.*;

/**
 * Implementations of the functions that were described
 * in Lab 1.
 * @author Tim Stullich
 * @version 1.0
 */
public class Operations {
    /**
     * Creates the intersection of two sets and returns that set
     * @param a The first set for the intersection
     * @param b The second set for the intersection
     * @return The set that holds all of the intersecting elements
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
     * Creates the union of two and returns a new set
     * @param a The first set for the union
     * @param b The second set for the union
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

    /**
     * Subtracts one set from another much like A - B
     * @param a The first set that will be subtracted from
     * @param b The second set that will subtract from A
     * @return The set that will hold the intersection
     */
    public static <T> Set<T> diff(Set<T> a, Set<T> b) {
        Set<T> c = new HashSet<T>();
        for (T ele : a) {
            if (!b.contains(ele)) {
                c.add(ele);
            }
        }
        return c;

    }

    /**
     * Tests if a set is a subset of the other
     * @param a The first set that will check if all elements
     *          are contained in B
     * @param b The "superset" to check entries from
     * @return True if set A is a subset
     *         False if set A is not a subset
     */
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
     * @param a The set on which to apply the filter function to
     * @param f The function that will be applied to set A
     * @return A new set that will have filtered according to F
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
     * @param a The set to which to map the filter to
     * @param f The function that will map to the set's elements
     * @return A new set to which the map function was applied to
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
     * @param a The set that we need to create the power set for
     * @return The finished power set
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
     * Returns a String with an error message if the integer is not in the range of 0-9
     * @param i The integer that needs to be converted to Unicode
     * @return The resulting Unicode in String format
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
