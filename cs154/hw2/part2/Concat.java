public class Concat implements RegEx {
    private RegEx first, second;

    public Concat(RegEx r1, RegEx r2) {
        first = r1;
        second = r2;
    }

    public boolean matches(String str) {
        if (str.length() == 0) {
            return true;
        }

        for (int i = 1; i <= str.length(); i++) {
            String sub = str.substring(0, i);
            if (!first.matches(sub)) {
                return matches(str.substring(i, str.length()));
            }
        }

        for (int i = 1; i <= str.length(); i++) {
            String sub = str.substring(0, i);
            if (!second.matches(sub)) {
                return matches(str.substring(i, str.length()));
            }
        }

        return false;
    }
}
