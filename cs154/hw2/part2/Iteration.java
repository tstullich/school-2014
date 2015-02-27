public class Iteration implements RegEx {
    private RegEx base;

    public Iteration(RegEx r1) {
        base = r1;
    }

    public boolean matches(String str) {
        if (str.length() == 1) {
            return base.matches(str);
        }
        if (!base.matches(Character.toString(str.charAt(0)))) {
            return false;
        }
        return matches(str.substring(1, str.length() - 1));
    }
}
