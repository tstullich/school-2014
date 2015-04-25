public class Action {
    private int newState;
    private int newBit;
    private int direction;

    public Action(int state, int bit, int direction) {
        newState = state;
        newBit = bit;
        this.direction = direction;
    }

    public int getState() {
        return newState;
    }

    public int getBit() {
        return newBit;
    }

    public int getDirection() {
        return direction;
    }

    public String toString() {
        return "state: " + newState + " bit: " + newBit + " direction: " + direction;
    }
}
