class Literal implements RegEx {
    private String token;

    public boolean matches(String str) {
        return false;
    }

    public Literal(String literal) {
        token = literal;
    }
}
