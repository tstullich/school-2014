public class Trigger {
    private int currentState;
    private int currentBit;

    public Trigger(int state, int bit) {
        currentState = state;
        currentBit = bit;
    }

    public int getState() {
        return currentState;
    }

    public int getCurrentBit() {
        return currentBit;
    }
}
