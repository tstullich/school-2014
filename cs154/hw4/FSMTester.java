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

        // Testing Regex
        FSM m2 = new FSM();
        m.addTransition('/', 0, 0);
        m.addTransition('0', 0, 1);
        m.addTransition('1', 0, 1);
        m.addTransition('2', 0, 1);
        m.addTransition('3', 0, 1);
        m.addTransition('4', 0, 1);
        m.addTransition('5', 0, 1);
        m.addTransition('6', 0, 1);
        m.addTransition('7', 0, 1);
        m.addTransition('8', 0, 1);
        m.addTransition('9', 0, 1);

        m.addTransition('/', 1, 1);
        m.addTransition('0', 1, 2);
        m.addTransition('1', 1, 2);
        m.addTransition('2', 1, 2);
        m.addTransition('3', 1, 2);
        m.addTransition('4', 1, 2);
        m.addTransition('5', 1, 2);
        m.addTransition('6', 1, 2);
        m.addTransition('7', 1, 2);
        m.addTransition('8', 1, 2);
        m.addTransition('9', 1, 2);

        m.addTransition('/', 2, 3);
        m.addTransition('0', 2, 2);
        m.addTransition('1', 2, 2);
        m.addTransition('2', 2, 2);
        m.addTransition('3', 2, 2);
        m.addTransition('4', 2, 2);
        m.addTransition('5', 2, 2);
        m.addTransition('6', 2, 2);
        m.addTransition('7', 2, 2);
        m.addTransition('8', 2, 2);
        m.addTransition('9', 2, 2);


        m.addTransition('/', 3, 3);
        m.addTransition('0', 3, 4);
        m.addTransition('1', 3, 4);
        m.addTransition('2', 3, 4);
        m.addTransition('3', 3, 4);
        m.addTransition('4', 3, 4);
        m.addTransition('5', 3, 4);
        m.addTransition('6', 3, 4);
        m.addTransition('7', 3, 4);
        m.addTransition('8', 3, 4);
        
        m.addTransition('/', 4, 4);
        m.addTransition('0', 4, 5);
        m.addTransition('1', 4, 5);
        m.addTransition('2', 4, 5);
        m.addTransition('3', 4, 5);
        m.addTransition('4', 4, 5);
        m.addTransition('5', 4, 5);
        m.addTransition('6', 4, 5);
        m.addTransition('7', 4, 5);
        m.addTransition('8', 4, 5);
        m.addTransition('9', 4, 5);


        m.addTransition('/', 5, 6);
        m.addTransition('0', 5, 5);
        m.addTransition('1', 5, 5);
        m.addTransition('2', 5, 5);
        m.addTransition('3', 5, 5);
        m.addTransition('4', 5, 5);
        m.addTransition('5', 5, 5);
        m.addTransition('6', 5, 5);
        m.addTransition('7', 5, 5);
        m.addTransition('8', 5, 5);
        m.addTransition('9', 5, 5);
        
        m.addTransition('/', 6, 6);
        m.addTransition('0', 6, 7);
        m.addTransition('1', 6, 7);
        m.addTransition('2', 6, 7);
        m.addTransition('3', 6, 7);
        m.addTransition('4', 6, 7);
        m.addTransition('5', 6, 7);
        m.addTransition('6', 6, 7);
        m.addTransition('7', 6, 7);
        m.addTransition('8', 6, 7);
        m.addTransition('9', 6, 7);

        m.addTransition('/', 7, 7);
        m.addTransition('0', 7, 8);
        m.addTransition('1', 7, 8);
        m.addTransition('2', 7, 8);
        m.addTransition('3', 7, 8);
        m.addTransition('4', 7, 8);
        m.addTransition('5', 7, 8);
        m.addTransition('6', 7, 8);
        m.addTransition('7', 7, 8);
        m.addTransition('8', 7, 8);
        m.addTransition('9', 7, 8);

        m.addTransition('/', 8, 7);
        m.addTransition('0', 8, 8);
        m.addTransition('1', 8, 8);
        m.addTransition('2', 8, 8);
        m.addTransition('3', 8, 8);
        m.addTransition('4', 8, 8);
        m.addTransition('5', 8, 8);
        m.addTransition('6', 8, 8);
        m.addTransition('7', 8, 8);
        m.addTransition('8', 8, 8);
        m.addTransition('9', 8, 8);

        m.addFinalState(8);

        System.out.println("02/01/15: " + m.accept("02/01/15"));
        System.out.println("2/01/15: " + m.accept("2/01/15"));
        System.out.println("02/1/15: " + m.accept("02/1/15"));
        System.out.println("02/01/1: " + m.accept("02/1/1"));
    }

    public static void main(String[] args) {
       test();
    }
}
