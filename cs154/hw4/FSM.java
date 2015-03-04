import java.util.HashMap;
import java.util.HashSet;

public class FSM {
    private HashSet<State> states;
    
    public FSM() {
        states = new HashSet<State>();
        states.add(new State(0));
    }

    public boolean accept(String str) {
        return false;
    }

    public void addFinalState(int state) {
    }
    
    public void addTransition(char qualifier, int from, int to) {
        Transition t = new Transition(qualifier, from, to);
    }

    public void reset() {
    }

    class State {
        private boolean isfinalState;
        private int stateID;
        private HashSet<Transition> transitions;

        private State(int stateID) {
            isfinalState = false;
            this.stateID = stateID;
            transitions = new HashSet<Transition>();
        }

        private void addTransition(Transition t) {
            transitions.add(t);
        }
    }
    
    class Transition { 
        private char qualifier;
        int from;
        int to;

        private Transition(char qualifier, int from, int to) {
            this.qualifier = qualifier;
            this.from = from;
            this.to = to;
        }
    }
}
