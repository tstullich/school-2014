public class Choice implements RegEx {
    private RegEx alt1, alt2;

    public Choice(RegEx r1, RegEx r2) {
        alt1 = r1;
        alt2 = r2;
    }

    public boolean matches(String str) {
        int counter = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            if (!alt1.matches(Character.toString(str.charAt(i))) &&
                !alt2.matches(Character.toString(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
