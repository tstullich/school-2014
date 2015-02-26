public class Literal implements RegEx {
    private String token;

    public boolean matches(String str) {
        return token.equals(str);
    }

    public Literal(String literal) {
        token = literal;
    }
}
