public class Concat implements RegEx {
    private RegEx first, second;

    public Concat(RegEx r1, RegEx r2) {
        first = r1;
        second = r2;
    }

    public boolean matches(String str) {
        System.out.println("Concat: " + str);
        int index = 0;
        while (index < str.length() - 1 &&
               first.matches(str.substring(index, str.length() - first.length()))) {
            index++;
        }
        if (index == 0) {
            return false;
        }
        while (index < str.length() - 1 &&
               second.matches(str.substring(index, str.length() - second.length()))) {
            index++;
        }
        return index == str.length() - 1;
    }

    public int length(){return 0;}
}
