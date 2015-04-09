public class Concatenation extends Result {
    protected Result first;
    protected Result second;

    @Override
    public String toString() {
        return "[ ~ " + first.toString() + " ] " + second.toString() + "]";
    }
}
