public class Option implements RegEx{
    private RegEx base;

    public Option(RegEx r1) {
        base = r1;
    }

    public boolean matches(String str) {
        if (str.length() == 0) {
            return true;
        }
        // Need to fix substring creation.
        for (int i = 0; i < str.length() - base.length(); i++) {
            String sub = str.substring(i, i + base.length());
            System.out.println("Subs: " + sub);
            if (!base.matches(sub)) {
                return false;
            }
        }
        return true;
    }

    public int length() { return 0; }
}
