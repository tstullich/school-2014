public class Literal implements RegEx {
    private String token;

    public Literal(String literal) {
        token = literal;
    }

    public boolean matches(String str) {
        System.out.println("Comparing " + token + " to " + str);
        return token.equals(str);
    }

    public int length() {
        return token.length();
    }
}
