public class Combinators {
    public static Parser alt(Parser p1, Parser p2) {
        Parser parser = new Parser();
        parser.setParser (
            result-> {
                if (result.fail) {
                    return result;
                }

                Choice answer = new Choice();
                answer.choice = p1.apply(result);
                if (answer.choice.fail) {
                    answer.choice = p2.apply(result);
                }
                if (answer.choice.fail) {
                    return answer.choice;
                }
                answer.unseen = answer.choice.unseen;
                return answer;
            });
            return parser;
    }

    public static Parser seq(Parser p1, Parser p2) {
        return null;
    }
    public static Parser rep(Parser p) {
        return null;
    }
    public static Parser opt(Parser p) {
        return null;
    }

    public static Parser regEx(String regEx) {
        Parser parser = new Parser();
        parser.setParser(
            result -> {
                if (result.fail) {
                    return result;
                }
                Literal literal = new Literal(regEx);
                return literal;
            });
        return parser;
    }
}
