public class Iteration implements RegEx {
    private RegEx base;

    public Iteration(RegEx r1) {
        base = r1;
    }

    public boolean matches(String str) {
        if (str.length() == 0) {
            return true;
        }

        for (int i = 1; i <= str.length(); i++) {
            String sub = str.substring(0, i);
            if (base.matches(sub)) {
                return matches(str.substring(i, str.length()));
            }
        }
        return false;
    }
}
