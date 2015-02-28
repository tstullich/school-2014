public class Choice implements RegEx {
    private RegEx alt1, alt2;

    public Choice(RegEx r1, RegEx r2) {
        alt1 = r1;
        alt2 = r2;
    }

    public boolean matches(String str) {
        if (str.length() == 0) {
            return true;
        }

        for (int i = 1; i <= str.length(); i++) {
            String sub = str.substring(0, i);
            if (alt1.matches(sub) || alt2.matches(sub)) {
                return matches(str.substring(i, str.length()));
            }
        }
        return false;
    }
}
