import java.util.*;

public class TuringMachine {
    private Tape tape;
    private Map<Trigger, Action> program;
    private Set<Integer> finalStates; // run halts when state is a member
    private int state; // the current state

    public TuringMachine(Tape input) {
        tape = input;
        state = 0;
    }

    public void addStep(Trigger t, Action a) {
        program.add(t, a);
    }

    public void addFinalState(int state) {
        finalStates.add(state);
    }

    public void run() { }
}
