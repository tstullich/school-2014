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
    /*  
     *  Commenting this out for now
     *  Parser parser = new Parser();
        parser.setParser(
            result -> {
                if (result.fail) {
                    return result;
                }
                Iteration 
            });*/
        return null;
    }
    public static Parser rep(Parser p) {
        Parser parser = new Parser();
        parser.setParser(
            result -> {
                if (result.fail) {
                    return result;
                }
                Iteration iteration = new Iteration();
                for (Result res : result.unseen) {
                }
            });
        return null;
    }
    public static Parser opt(Parser p) {
        Parser parser = new Parser();
        parser.setParser(
            result -> {
                if (result.fail) {
                    return result;
                }
                Option opt = new Option();
                opt.option.apply(result);
                if (opt.option.fail) {
                    return opt;
                }
            })
        return null;
    }

    public static Parser regEx(String regEx) {
        Parser parser = new Parser();
        parser.setParser(
            result -> {
                // No more tokens to parse. We are done
                if (result.unseen.size() == 0) {
                    result.fail = true;
                    return result;
                }

                // Need to check tokens if they match
                String token = result.unseen.remove(0);
                if (token.matches(regEx)) {
                    Literal literal = new Literal(token);
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
