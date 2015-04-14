public class DemoParsers {
    public static Parser number = Combinators.regEx("[0-9]+");
    public static Parser name = Combinators.regEx("[a-zA-Z][a-zA-Z0-9]*");
    public static Parser trueBool = Combinators.regEx("true");
    public static Parser falseBool = Combinators.regEx("false");
    public static Parser andOperator = Combinators.regEx("&&");
    public static Parser orOperator = Combinators.regEx("\\|\\||");
    public static Parser plusOperator = Combinators.regEx("\\+");
    public static Parser multOperator = Combinators.regEx("\\*");
    public static Parser negateOperator = Combinators.regEx("!");

    public static Parser boolParser = new Parser();
    static {
        boolParser.setParser(
            Combinators.alt(trueBool, falseBool));
    }

    public static Parser operatorParser = new Parser();
    static {
        operatorParser.setParser(
            Combinators.alt(andOperator,
                    Combinators.alt(orOperator,
                        Combinators.alt(plusOperator,
                            Combinators.alt(multOperator, negateOperator)))));
    }

    public static Parser termParser = new Parser();
    static {
        termParser.setParser(
            Combinators.alt(number,
                Combinators.alt(name, boolParser)));
    }

    public static Parser productParser = new Parser();
    static {
        productParser.setParser(
                Combinators.alt(termParser,
                    Combinators.seq(termParser,
                        Combinators.rep(Combinators.seq(operatorParser, termParser)))));
    }
}
