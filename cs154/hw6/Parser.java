import java.util.function;

public class Parser {
    protected UnaryOperator<Result> parser;
    public Parser() {
        parser = new UnaryOperator<Result>();
    }

    public Result apply(Result r) {
        parser.apply(Result);
        return r;
    }

    public void setParser(UnaryOperator<Result> p) {
        parser = p;
    }
}
