public class Literal implements RegEx {
    private String token;

    public Literal(String literal) {
        token = literal;
    }

    public boolean matches(String str) {
        return token.equals(str);
    }
}
