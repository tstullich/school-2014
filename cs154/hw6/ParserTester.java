public class ParserTester {
    public static void test(Parser p, String s) {
        System.out.println("s = " + s);
        Result tree = p.apply(new Result(s));
        System.out.println("tree = " + tree);
        System.out.println("pending = " + tree.pending());
    }

    public static void testExpParser() {
        String s = "42";
        test(DemoParsers.number, s);
        s = "29z";
        test(DemoParsers.number, s);
        s = "Tim";
        test(DemoParsers.name, s);
        s = "true false";
        test(DemoParsers.boolParser, s);
        s = "*";
        test(DemoParsers.operatorParser, s);
        s = "-";
        test(DemoParsers.operatorParser, s);
        s = "123";
        test(DemoParsers.termParser, s);
        s = "123 + 12";
        test(DemoParsers.productParser, s);
    }

    public static void main(String[] args) {
        testExpParser();
    }
}
