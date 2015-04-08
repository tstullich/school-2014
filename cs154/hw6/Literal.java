public class Literal extends Result {
    protected String token;

    public Literal(String token) {
        this.token = token;
    }

    public String toString() {
        return token;
    }
}
