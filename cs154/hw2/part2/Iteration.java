public class Iteration implements RegEx {
    private RegEx base;

    public Iteration(RegEx r1) {
        base = r1;
    }

    public boolean matches(String str) {
        for (int i = 0; i < str.length() - base.length(); i++) {
            String sub = str.substring(i, i + base.length());
            System.out.println("Subs1: " + sub);
            if (!base.matches(sub)) {
                return false;
            }
        }
        return true;
    }

    public int length() {
        return base.length();
    }
}
