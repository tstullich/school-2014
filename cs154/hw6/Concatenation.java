public class Concatenation extends Result {
    protected Result first;
    protected Result second;

    public String toString() {
        return "[ ~ " + first.toString() + " ] " + second.toString() + "]";
    }
}
