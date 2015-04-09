public class Choice extends Result {
    protected Result choice;

    public Choice() {}

    public String toString() {
        return "[ | " + choice.toString() + " ]";
    }
}
