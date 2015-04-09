public class Literal extends Result {
    protected String token;

    public Literal() {}

    public Literal(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "<" + token + ">" ;
    }
}
