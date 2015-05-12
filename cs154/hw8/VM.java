import java.util.*;
import java.io.*;

public class VM {
    private Integer pc;
    private Map<String, Integer> vars;
    private ArrayList<Command> program;
    private int executedInstructions;

    public VM() {
        pc = 0;
        executedInstructions = 0;
        vars = new HashMap<String, Integer>();
        program = new ArrayList<Command>();
    }

    public void add(String cmd) {
        program.add(new Command(cmd, pc++));
    }

    private void resolveLabels() {
        Stack<Command> loopStack = new Stack<Command>();
        Map<String, Integer> targets = new HashMap<String, Integer>();

        for (Command cmd : program) {
            if (cmd.getLabel() != null) {
               targets.put(cmd.getLabel(), cmd.getPc());
            }
            if (cmd.getOpcode().equals("loop")) {
                loopStack.push(cmd);
            }
            if (cmd.getOpcode().equals("end")) {
                Command loopCmd = loopStack.pop();
                loopCmd.setTarget(cmd.getPc());
                cmd.setTarget(loopCmd.getPc());
            }
        }

        for (Command cmd : program) {
           if (cmd.getOpcode().equals("goto")) {
               cmd.setTarget(targets.get(cmd.getArg1()));
           }
        }
    }

    public void compile(String fileName) {
        try {
            FileReader input = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(input);
            String line;
            while ((line = reader.readLine()) != null) {
                program.add(new Command(line, pc++));
            }
            reader.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void execute(Command cmd) {
        if (cmd.getOpcode().equals("load")) {
            vars.put(cmd.getArg1(), Integer.parseInt(cmd.getArg2()));
        }
        else if (cmd.getOpcode().equals("inc")) {
            if (vars.containsKey(cmd.getArg1())) {
                int var = vars.get(cmd.getArg1());
                vars.put(cmd.getArg1(), var + 1);
            }
            else {
                vars.put(cmd.getArg1(), 0);
            }
        }
        else if (cmd.getOpcode().equals("goto")) {
            // Set pc
            pc = cmd.getTarget();
        }
        else if (cmd.getOpcode().equals("loop")) {
            // If it's an actual number parse directly
            if (isNumeric(cmd.getArg1())) {
                cmd.setCount(Integer.parseInt(cmd.getArg1()));
            }
            else {
                if (vars.containsKey(cmd.getArg1())) {
                    int newCount = vars.get(cmd.getArg1());
                    cmd.setCount(newCount);
                }
                else {
                    int temp = 0;
                    cmd.setCount(0);
                    vars.put(cmd.getArg1(), temp);
                }
            }
            if (cmd.getCount() <= 0) {
                pc = cmd.getTarget() + 1;
            }
        }
        else if (cmd.getOpcode().equals("end")) {
            Command loop = program.get(cmd.getTarget());
            loop.setCount(loop.getCount() - 1);
            if (loop.getCount() > 0) {
                pc = loop.getPc() + 1;
            }
        }
        else {
            System.out.println("Invalid instruction encountered. Halting execution");
            return;
        }
    }

    public void run() {
        resolveLabels();
        pc = 0;
        while(pc < program.size()) {
            execute(program.get(pc++));
            executedInstructions++;
        }
    }

    public void printStats() {
        System.out.println("PC: " + pc);
        System.out.println("Executed Instructions: " + executedInstructions);
        System.out.println("Variables:");
        for (Map.Entry<String, Integer> entry : vars.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    private boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }
}
