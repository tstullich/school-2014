import java.util.function.*;

public class Enumerator implements Predicate<Integer> {
    public boolean test(Integer t) {
        return t == 0;
    }
}
