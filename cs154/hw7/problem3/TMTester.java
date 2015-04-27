public class TMTester {
    public static void test2() {
        Tape tape = new Tape("A1111B");
        TuringMachine tm = new TuringMachine(tape);
        tm.addFinalState(6);

        // Seek a 1
        Trigger t = new Trigger(0, 'A');
        Action a = new Action(0, 'A', 1);
        tm.addStep(t, a);

        t = new Trigger(0, '1');
        a = new Action(1, '0', 1);
        tm.addStep(t, a);

        t = new Trigger(0, '0');
        a = new Action(0, '0', 1);
        tm.addStep(t, a);

        // Finish state
        t = new Trigger(0, 'B');
        a = new Action(6, 'B', 0);
        tm.addStep(t, a);

        // Seek write start
        t = new Trigger(1, '1');
        a = new Action(1, '1', 1);
        tm.addStep(t, a);

        t = new Trigger(1, '0');
        a = new Action(1, '0', 1);
        tm.addStep(t, a);

        t = new Trigger(1, 'B');
        a = new Action(2, 'B', 1);
        tm.addStep(t, a);

        // Write Stage
        t = new Trigger(2, '0');
        a = new Action(3, '1', 1);
        tm.addStep(t, a);

        t = new Trigger(2, '1');
        a = new Action(2, '1', 1);
        tm.addStep(t, a);

        t = new Trigger(3, '0');
        a = new Action(4, '1', 1);
        tm.addStep(t, a);

        t = new Trigger(3, '1');
        a = new Action(3, '1', 1);
        tm.addStep(t, a);

        t = new Trigger(4, '0');
        a = new Action(5, '1', 0);
        tm.addStep(t, a);

        // Seek back stage
        t = new Trigger(5, '1');
        a = new Action(5, '1', -1);
        tm.addStep(t, a);

        t = new Trigger(5, 'B');
        a = new Action(5, 'B', -1);
        tm.addStep(t, a);

        t = new Trigger(5, '0');
        a = new Action(5, '0', -1);
        tm.addStep(t, a);

        t = new Trigger(5, 'A');
        a = new Action(0, 'A', 0);
        tm.addStep(t, a);
        tm.run();
    }

    public static void test1() {
        Tape tape = new Tape("1111101111110");
        TuringMachine tm = new TuringMachine(tape);
        tm.addFinalState(3);
        tm.addFinalState(4);

        Trigger t = new Trigger(0, '1');
        Action a = new Action(0, '1', 1);
        tm.addStep(t, a);

        t = new Trigger(0, '0');
        a = new Action(1, '0', 1);
        tm.addStep(t, a);

        t = new Trigger(1, '0');
        a = new Action(3, '0', 0);
        tm.addStep(t, a);

        t = new Trigger(1, '1');
        a = new Action(2, '0', -1);
        tm.addStep(t, a);

        t = new Trigger(2, '0');
        a = new Action(0, '1', 1);
        tm.addStep(t, a);

        t = new Trigger(2, '1');
        a = new Action(4, '1', 1);
        tm.addStep(t, a);
        tm.run();
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
