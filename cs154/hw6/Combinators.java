public class Combinators {
    public static Parser alt(Parser p1, Parser p2) {
        Parser parser = new Parser();
        parser.setParser(
            result -> {
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
        Parser parser = new Parser();
        parser.setParser(
            result -> {
                if (result.fail) {
                    return result;
                }

                Concatenation concatenation = new Concatenation();
                concatenation.first = p1.apply(result);
                if (concatenation.first.fail)  {
                    return result;
                }

                concatenation.unseen = concatenation.first.unseen;
                concatenation.second = p2.apply(result);
                if (concatenation.second.fail) {
                    return result;
                }

                concatenation.unseen = concatenation.second.unseen;
                return concatenation;
            });
        return parser;
    }

    public static Parser rep(Parser p) {
        Parser parser = new Parser();
        parser.setParser(
            result -> {
                if (result.fail) {
                    return result;
                }

                Iteration iteration = new Iteration();
                while (!result.fail) {
                    p.apply(result);
                    iteration.iterations.add(result);
                }

                return iteration;
            });
        return parser;
    }

    public static Parser opt(Parser p) {
        Parser parser = new Parser();
        parser.setParser(
            result -> {
                if (result.fail) {
                    return result;
                }

                Option option = new Option();
                option.option = p.apply(result);
                if (option.option.fail) {
                    return result;
                }
                else {
                    return option;
                }
            });
        return parser;
    }

    public static Parser regEx(String regEx) {
        Parser parser = new Parser();
        parser.setParser(
            result -> {
                Literal literal = new Literal();
                if (result.unseen.isEmpty()) {
                    literal.fail = true;
                    return literal;
                }

                literal.token = result.unseen.get(0);
                if (literal.token.matches(regEx)) {
                    result.unseen.remove(0);
                    literal.unseen = result.unseen;
                }
                else {
                    literal.fail = true;
                    literal.unseen = result.unseen;
                }
                return literal;
            });
        return parser;
    }
}
