public class Concat implements RegEx {
    private RegEx first, second;

    public Concat(RegEx r1, RegEx r2) {
        first = r1;
        second = r2;
    }

    public boolean matches(String str) {
        int index = 0;
        while (index < str.length() - 1 &&
               first.matches(Character.toString(str.charAt(index)))) {
            index++;
        }

        if (index == 0) {
            return false;
        }

        while (index < str.length() - 1 &&
               second.matches(Character.toString(str.charAt(index)))) {
            index++;
        }

        return index == str.length() - 1;
    }
}
