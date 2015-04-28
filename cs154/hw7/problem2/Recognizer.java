import java.util.function.*;

public class Recognizer implements Predicate<Integer> {
    public boolean test(Integer t) {
        return t == 0;
    }
}
