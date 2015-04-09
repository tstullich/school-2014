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
        return null;
    }

    public static Parser opt(Parser p) {
        return null;
    }

    public static Parser regEx(String regEx) {
        Parser parser = new Parser();
        parser.setParser(
            result -> {
                // Parsing failed earlier. We are done
                if (result.fail) {
                    result.fail = true;
                    return result;
                }

                Literal literal = new Literal();
                // Need to handle an empty list somehow
                if (result.unseen.size() == 0) {
                    literal.fail = true;
                    return literal;
                }

                // Need to check token against regex
                String token = result.unseen.remove(0);
                if (token.matches(regEx)) {
                    literal = new Literal(token);
                    return literal;
                }
                else {
                    result.fail = true;
                    return result;
                }
            });
        return parser;
    }
}
