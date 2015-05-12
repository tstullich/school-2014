import java.io.PrintStream;

public class TestVM {
    public static void basicTest() {
         try {
            VM vm = new VM();
            vm.add("load x, 10");
            vm.add("load y, 5");
            vm.add("loop x");
            vm.add("inc y");
            vm.add("end");
            vm.add("goto AAA");
            vm.add("inc y");
            vm.add("AAA: inc y");
            vm.run();
            vm.printStats();
        } catch (Exception e) {
            e.printStackTrace(new PrintStream(System.out));
        }
    }

    public static void additionTest() {
        try {
            VM vm = new VM();
            vm.add("load m, 10");
            vm.add("load n, 5");
            vm.add("loop n");
            vm.add("inc m");
            vm.add("end");
            vm.run();
            vm.printStats();
        } catch (Exception e) {
            e.printStackTrace(new PrintStream(System.out));
        }
    }

    public static void subtractionTest() {
        try {
            VM vm = new VM();
            vm.add("load v, 10");
            vm.add("load y, 4");
            vm.add("loop y");
            // Beginning of x-1 macro
            vm.add("load w, 0");
            vm.add("loop x");
            vm.add("load v, w");
            vm.add("inc w");
            vm.add("end");
            vm.add("load y, 5");
            vm.add("end");
            vm.add("load z, v");
            vm.run();
            vm.printStats();
        }
        catch (Exception e) {
            e.printStackTrace(new PrintStream(System.out));
        }
    }

    public static void multiplicationTest() {
        try {
            VM vm = new VM();
            vm.add("load n, 2");
            vm.add("load m, 10");
            vm.add("load x, 4");
            vm.add("loop n");
                vm.add("loop x");
                    vm.add("inc m");
                vm.add("end");
            vm.add("end");
            vm.run();
            vm.printStats();
        }
        catch (Exception e) {
            e.printStackTrace(new PrintStream(System.out));
        }
    }

    public static void main(String[] args) {
        basicTest();
        additionTest();
        //subtractionTest();
        multiplicationTest();
    }
}
