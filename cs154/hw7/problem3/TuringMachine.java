import java.util.*;

public class TuringMachine {
    private Tape tape;
    private Map<Trigger, Action> program;
    private Set<Integer> finalStates; // run halts when state is a member
    private int state; // the current state

    public TuringMachine(Tape input) {
        tape = input;
        program = new HashMap<Trigger, Action>();
        finalStates = new HashSet<Integer>();
        state = 0;
    }

    public void addStep(Trigger t, Action a) {
        program.put(t, a);
    }

    public void addFinalState(int state) {
        finalStates.add(state);
    }

    public void run() {
        while (!finalStates.contains(state)) {
            System.out.println("state = " + state + " index = " + tape.getHead());
            Action nextAction = getNextAction(state, tape.read());
            if (nextAction == null) {
                System.out.println("No new state found. Exiting");
                return;
            }
            state = nextAction.getState();
            tape.write(nextAction.getBit());
            tape.moveHead(nextAction.getDirection());
        }
        System.out.println("Program halted. Result: " + tape.result());
    }

    private Action getNextAction(int currentState, int currentBit) {
        for (Trigger t : program.keySet()) {
            if ((t.getState() == state) && (t.getCurrentBit() == currentBit)) {
                return program.get(t);
            }
        }
        return null;
    }
}
