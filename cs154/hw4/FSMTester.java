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

        // Testing Natural Number Regex
        FSM natFSM = new FSM();
        natFSM.addTransition('0', 0, 0);
        natFSM.addTransition('1', 0, 1);
        natFSM.addTransition('2', 0, 1);
        natFSM.addTransition('3', 0, 1);
        natFSM.addTransition('3', 0, 1);
        natFSM.addTransition('4', 0, 1);
        natFSM.addTransition('5', 0, 1);
        natFSM.addTransition('6', 0, 1);
        natFSM.addTransition('7', 0, 1);
        natFSM.addTransition('8', 0, 1);
        natFSM.addTransition('9', 0, 1);

        natFSM.addTransition('0', 1, 2);
        natFSM.addTransition('1', 1, 2);
        natFSM.addTransition('2', 1, 2);
        natFSM.addTransition('3', 1, 2);
        natFSM.addTransition('4', 1, 2);
        natFSM.addTransition('5', 1, 2);
        natFSM.addTransition('6', 1, 2);
        natFSM.addTransition('7', 1, 2);
        natFSM.addTransition('8', 1, 2);
        natFSM.addTransition('9', 1, 2);

        natFSM.addTransition('0', 2, 2);
        natFSM.addTransition('1', 2, 2);
        natFSM.addTransition('2', 2, 2);
        natFSM.addTransition('3', 2, 2);
        natFSM.addTransition('4', 2, 2);
        natFSM.addTransition('5', 2, 2);
        natFSM.addTransition('6', 2, 2);
        natFSM.addTransition('7', 2, 2);
        natFSM.addTransition('8', 2, 2);
        natFSM.addTransition('9', 2, 2);

        natFSM.addFinalState(0);
        natFSM.addFinalState(1);
        natFSM.addFinalState(2);

        System.out.println("0: " + natFSM.accept("0"));
        System.out.println("1: " + natFSM.accept("1"));
        System.out.println("10: " + natFSM.accept("10"));
        System.out.println("120: " + natFSM.accept("120"));

        // Testing Date Regex
        FSM dateFSM = new FSM();
        dateFSM.addTransition('/', 0, 0);
        dateFSM.addTransition('0', 0, 1);
        dateFSM.addTransition('1', 0, 1);
        dateFSM.addTransition('2', 0, 1);
        dateFSM.addTransition('3', 0, 1);
        dateFSM.addTransition('4', 0, 1);
        dateFSM.addTransition('5', 0, 1);
        dateFSM.addTransition('6', 0, 1);
        dateFSM.addTransition('7', 0, 1);
        dateFSM.addTransition('8', 0, 1);
        dateFSM.addTransition('9', 0, 1);

        dateFSM.addTransition('/', 1, 1);
        dateFSM.addTransition('0', 1, 2);
        dateFSM.addTransition('1', 1, 2);
        dateFSM.addTransition('2', 1, 2);
        dateFSM.addTransition('3', 1, 2);
        dateFSM.addTransition('4', 1, 2);
        dateFSM.addTransition('5', 1, 2);
        dateFSM.addTransition('6', 1, 2);
        dateFSM.addTransition('7', 1, 2);
        dateFSM.addTransition('8', 1, 2);
        dateFSM.addTransition('9', 1, 2);

        dateFSM.addTransition('/', 2, 3);
        dateFSM.addTransition('0', 2, 2);
        dateFSM.addTransition('1', 2, 2);
        dateFSM.addTransition('2', 2, 2);
        dateFSM.addTransition('3', 2, 2);
        dateFSM.addTransition('4', 2, 2);
        dateFSM.addTransition('5', 2, 2);
        dateFSM.addTransition('6', 2, 2);
        dateFSM.addTransition('7', 2, 2);
        dateFSM.addTransition('8', 2, 2);
        dateFSM.addTransition('9', 2, 2);

        dateFSM.addTransition('/', 3, 3);
        dateFSM.addTransition('0', 3, 4);
        dateFSM.addTransition('1', 3, 4);
        dateFSM.addTransition('2', 3, 4);
        dateFSM.addTransition('3', 3, 4);
        dateFSM.addTransition('4', 3, 4);
        dateFSM.addTransition('5', 3, 4);
        dateFSM.addTransition('6', 3, 4);
        dateFSM.addTransition('7', 3, 4);
        dateFSM.addTransition('8', 3, 4);
        
        dateFSM.addTransition('/', 4, 4);
        dateFSM.addTransition('0', 4, 5);
        dateFSM.addTransition('1', 4, 5);
        dateFSM.addTransition('2', 4, 5);
        dateFSM.addTransition('3', 4, 5);
        dateFSM.addTransition('4', 4, 5);
        dateFSM.addTransition('5', 4, 5);
        dateFSM.addTransition('6', 4, 5);
        dateFSM.addTransition('7', 4, 5);
        dateFSM.addTransition('8', 4, 5);
        dateFSM.addTransition('9', 4, 5);

        dateFSM.addTransition('/', 5, 6);
        dateFSM.addTransition('0', 5, 5);
        dateFSM.addTransition('1', 5, 5);
        dateFSM.addTransition('2', 5, 5);
        dateFSM.addTransition('3', 5, 5);
        dateFSM.addTransition('4', 5, 5);
        dateFSM.addTransition('5', 5, 5);
        dateFSM.addTransition('6', 5, 5);
        dateFSM.addTransition('7', 5, 5);
        dateFSM.addTransition('8', 5, 5);
        dateFSM.addTransition('9', 5, 5);
        
        dateFSM.addTransition('/', 6, 6);
        dateFSM.addTransition('0', 6, 7);
        dateFSM.addTransition('1', 6, 7);
        dateFSM.addTransition('2', 6, 7);
        dateFSM.addTransition('3', 6, 7);
        dateFSM.addTransition('4', 6, 7);
        dateFSM.addTransition('5', 6, 7);
        dateFSM.addTransition('6', 6, 7);
        dateFSM.addTransition('7', 6, 7);
        dateFSM.addTransition('8', 6, 7);
        dateFSM.addTransition('9', 6, 7);

        dateFSM.addTransition('/', 7, 7);
        dateFSM.addTransition('0', 7, 8);
        dateFSM.addTransition('1', 7, 8);
        dateFSM.addTransition('2', 7, 8);
        dateFSM.addTransition('3', 7, 8);
        dateFSM.addTransition('4', 7, 8);
        dateFSM.addTransition('5', 7, 8);
        dateFSM.addTransition('6', 7, 8);
        dateFSM.addTransition('7', 7, 8);
        dateFSM.addTransition('8', 7, 8);
        dateFSM.addTransition('9', 7, 8);

        dateFSM.addTransition('/', 8, 7);
        dateFSM.addTransition('0', 8, 8);
        dateFSM.addTransition('1', 8, 8);
        dateFSM.addTransition('2', 8, 8);
        dateFSM.addTransition('3', 8, 8);
        dateFSM.addTransition('4', 8, 8);
        dateFSM.addTransition('5', 8, 8);
        dateFSM.addTransition('6', 8, 8);
        dateFSM.addTransition('7', 8, 8);
        dateFSM.addTransition('8', 8, 8);
        dateFSM.addTransition('9', 8, 8);

        dateFSM.addFinalState(8);

        System.out.println("02/01/15: " + dateFSM.accept("02/01/15"));
        System.out.println("2/01/15: " + dateFSM.accept("2/01/15"));
        System.out.println("02/1/15: " + dateFSM.accept("02/1/15"));
        System.out.println("02/01/1: " + dateFSM.accept("02/1/1"));

        // Testing name Regex
        FSM nameFSM = new FSM();
        nameFSM.addTransition('a', 0, 1);
        nameFSM.addTransition('b', 0, 1);
        nameFSM.addTransition('c', 0, 1);

        nameFSM.addTransition('a', 1, 1);
        nameFSM.addTransition('b', 1, 1);
        nameFSM.addTransition('c', 1, 1);
        nameFSM.addTransition('0', 1, 1);
        nameFSM.addTransition('1', 1, 1);
        nameFSM.addTransition('2', 1, 1);
        nameFSM.addTransition('3', 1, 1);
        nameFSM.addTransition('4', 1, 1);
        nameFSM.addTransition('5', 1, 1);
        nameFSM.addTransition('6', 1, 1);
        nameFSM.addTransition('7', 1, 1);
        nameFSM.addTransition('8', 1, 1);
        nameFSM.addTransition('9', 1, 1);

        nameFSM.addFinalState(1);
        System.out.println("a: " + nameFSM.accept("a"));
        System.out.println("ab: " + nameFSM.accept("ab"));
        System.out.println("a0: " + nameFSM.accept("a0"));
        System.out.println("aa0: " + nameFSM.accept("aa0"));
    }

    public static void main(String[] args) {
       test();
    }
}
