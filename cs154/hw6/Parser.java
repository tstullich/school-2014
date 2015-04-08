import java.util.function.*;

public class Parser implements UnaryOperator<Result> {
    protected UnaryOperator<Result> parser;

    public Result apply(Result r) {
        return parser.apply(r);
    }

    public void setParser(UnaryOperator<Result> p) {
        parser = p;
    }
}
