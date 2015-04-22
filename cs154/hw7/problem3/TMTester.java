public class TMTester {
    public static void main(String[] args) {
        Tape tape = new Tape("A111111B11111C");
        TuringMachine tm = new TuringMachine(tape);
        tm.addFinalState('C');
        tm.run();
    }
}
