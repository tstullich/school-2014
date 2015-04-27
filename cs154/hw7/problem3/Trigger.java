public class Trigger {
    private int currentState;
    private char currentBit;

    public Trigger(int state, char bit) {
        currentState = state;
        currentBit = bit;
    }

    public int getState() {
        return currentState;
    }

    public char getCurrentBit() {
        return currentBit;
    }
}
