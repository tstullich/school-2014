public class TMTester {
    public static void main(String[] args) {
        Tape tape = new Tape("1111101111110");
        TuringMachine tm = new TuringMachine(tape);
        tm.addFinalState(3);
        tm.addFinalState(4);

        Trigger t = new Trigger(0, 1);
        Action a = new Action(0, 1, 1);
        tm.addStep(t, a);

        t = new Trigger(0, 0);
        a = new Action(1, 0, 1);
        tm.addStep(t, a);

        t = new Trigger(1, 0);
        a = new Action(3, 0, 0);
        tm.addStep(t, a);
        
        t = new Trigger(1, 1);
        a = new Action(2, 0, -1);
        tm.addStep(t, a);
        
        t = new Trigger(2, 0);
        a = new Action(0, 1, 1);
        tm.addStep(t, a);

        t = new Trigger(2, 1);
        a = new Action(4, 1, 1);
        tm.addStep(t, a);
        tm.run();
    }
}
