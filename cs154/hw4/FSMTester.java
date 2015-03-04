public class FSMTester {
    public static void test() {
          FSM m = new FSM();
          m.addTransition('0', 0, 1);
          m.addTransition('0', 1, 1);
          m.addTransition('1', 1, 2);
          m.addTransition('0', 2, 3);
          m.addTransition('1', 2, 2);
          m.addTransition('0', 3, 3);
          m.addFinalState(3);

          System.out.println("0011100: "+ m.accept("0011100"));
          System.out.println("01100: "+ m.accept("01100"));
          System.out.println("11100: "+ m.accept("11100"));
          System.out.println("001110011: "+ m.accept("001110011"));

          m.reset(); // clear all transitions and final states
         
       }

    public static void main(String[] args) {
       test();
    }
}
