import java.util.function.*;

public class Decider implements Predicate<Integer> {
    public boolean test(Integer t) {
        return t == 0;
    }
}
