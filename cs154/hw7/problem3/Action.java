public class Action {
    private int newState;
    private char newBit;
    private int direction;

    public Action(int state, char bit, int direction) {
        newState = state;
        newBit = bit;
        this.direction = direction;
    }

    public int getState() {
        return newState;
    }

    public char getBit() {
        return newBit;
    }

    public int getDirection() {
        return direction;
    }

    public String toString() {
        return "state: " + newState + " bit: " + newBit + " direction: " + direction;
    }
}
