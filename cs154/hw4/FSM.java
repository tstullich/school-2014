import java.util.HashMap;
import java.util.HashSet;

public class FSM {
    private HashSet<State> states;
    private State startState;
    
    public FSM() {
        states = new HashSet<State>();
        startState = new State(0);
        states.add(startState);
    }

    private State nextState(State currentState, char qualifier) {
        int nextState = currentState.hasTransition(qualifier);
        if (nextState == -1) {
           return null; 
        }

        for (State s : states) {
            if (s.getId() == nextState) {
                return s;
            }
        }
        return currentState;
    }

    public boolean accept(String str) {
        State currentState = startState;
        for (char s : str.toCharArray()) {
            if (currentState == null) {
                return false;
            }
            currentState = nextState(currentState, s);
        }
        return currentState.getFinalState();
    }

    public void addFinalState(int state) {
        for (State s : states) {
            if (s.getId() == state) {
                s.setFinalState(true);
            }
        }
    }
    
    public void addTransition(char qualifier, int from, int to) {
        State state = null;
        for (State s : states) {
            if (s.getId() == from) {
                state = s;
            }
        }

        if (state == null) {
            state = new State(from);
        }
        state.addTransition(new Transition(qualifier, to));

        // Need to update starting state
        if (from == 0) {
            startState = state;
        }
        states.add(state);
    }

    public void reset() {
        states.clear();
        startState.reset();
        states.add(startState);
    }

    class State {
        private boolean isFinalState;
        private int stateId;
        private HashSet<Transition> transitions;

        private State(int stateId) {
            isFinalState = false;
            this.stateId = stateId;
            transitions = new HashSet<Transition>();
        }

        private void addTransition(Transition t) {
            transitions.add(t);
        }

        private HashSet<Transition> getTransitions() {
            return transitions;
        }

        private int hasTransition(char qualifier) {
            for (Transition t : transitions) {
                if (t.getQualifier() == qualifier) {
                    return t.getTo();
                }
            }
            return -1;
        }

        private void reset() {
            isFinalState = false;
            transitions.clear();
        }

        private boolean getFinalState() {
            return isFinalState;
        }

        private void setFinalState(boolean state) {
            isFinalState = state;
        }

        private int getId() {
            return stateId;
        }

        private void setId(int stateId) {
            this.stateId = stateId;
        }
    }
    
    class Transition { 
        private char qualifier;
        private int to;

        private Transition(char qualifier, int to) {
            this.qualifier = qualifier;
            this.to = to;
        }

        private char getQualifier() {
            return qualifier;
        }

        private int getTo() {
            return to;
        }
    }
}
