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
            if (first.matches(sub)) {
                second.matches(str.substring(i, str.length()));
            }
        }

        for (int j = 1; j <= str.length(); j++) {
            String sub = str.substring(0, j);
            if (second.matches(sub)) {
                return second.matches(str.substring(j, str.length()));
            }
        }

        return false;
    }
}
