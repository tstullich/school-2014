public class Option implements RegEx{
    private RegEx base;

    public Option(RegEx r1) {
        base = r1;
    }

    public boolean matches(String str) {
        if (str.length() == 0) {
            return true;
        }

        for (int i = 0; i < str.length() - 1; i++) {
            if (!base.matches(Character.toString(str.charAt(i)))) {
                return false;
            }
        }

        return true;
    }
}
