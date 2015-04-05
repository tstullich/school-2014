import java.util.function;

public class Parser {
    private UnaryOperator<Result> parser;
    public Parser() {
        parser = new UnaryOperator<Result>();
    }

    public Result apply(Result r) {
        parser.apply(Result);
    }
}
