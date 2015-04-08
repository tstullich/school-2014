public class DemoParsers {
    public static void test(Parser p, String s) {
        System.out.println("s = " + s);
        Result tree = p.apply(new Result(s));
        System.out.println("tree = " + tree);
        System.out.println("pending = " + tree.pending());
    }

    public static void testExpParser() {
        String s = "42";
        test(ExpParsers.number, s);
        s = "29z";
        test(ExpParsers.number, s);
        s = "*";
        test(ExpParsers.operator, s);
        s = "-";
        test(ExpParsers.operator, s);
        s = "42 + 91 * 13 + 2";
        test(ExpParsers.exp, s);
        s = "123";
        test(ExpParsers.exp, s);
        s = "15 * 6 - 1-0";
        test(ExpParsers.exp, s);
    }

    public static void main(String[] args) {
        testExpParser();
    }
}
