public class DemoParsers {
    public static void test(Parser p, String s) {
        System.out.println("s = " + s);
        Result tree = p.apply(new Result(s));
        System.out.println("tree = " + tree);
        System.out.println("pending = " + tree.pending());
    }

    public static void main(String[] args) {
    }
}
