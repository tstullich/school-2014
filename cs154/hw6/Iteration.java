import java.util.ArrayList;

public class Iteration extends Result {
    protected ArrayList<Result> iterations;

    public Iteration() {
        iterations = new ArrayList<Result>();
    }

    public String toString() {
        String s = "";
        for (Result r : iterations) {
            s += r.toString();
        }
        return s;
    }
}
