import java.util.regex.*;

public class Command {
    private String label;
    private String opCode;
    private String arg1;
    private String arg2;
    private int target;
    private int pc;
    private int count; // used for the loop command
    private static String regEx = "([A-Z]+: )?((goto) ([A-Z]+)|(load) ([a-z]+), ([a-z0-9]+)?|(inc|loop|load) ([a-z]+)|(end))";
    private static Pattern cmmdPattern = Pattern.compile(regEx);

    public Command(String command, int pc) {
        this.pc = pc;
        System.out.println(command);
        Matcher match = cmmdPattern.matcher(command);

        if(match.matches()) {
            if (match.group(1) != null && !match.group(1).isEmpty()) {
                String[] s = match.group(1).split(":");
                label = s[0];
            }

            if (match.group(2) != null && match.group(2).contains("load")) {
                if (!match.group(2).contains(",")) {
                    if (match.group(8) != null) {
                        opCode = match.group(8);
                    }
                    if(match.group(9) != null) {
                        arg1 = match.group(9);
                    }
                }

                if(match.group(5) != null) {
                    opCode = match.group(5);
                }
                if(match.group(6) != null) {
                    arg1 = match.group(6);
                }
                if(match.group(7) != null) {
                    arg2 = match.group(7);
                }
            }
            else if (match.group(2) != null && match.group(2).contains("inc")) {
                if(match.group(8) != null) {
                    opCode = match.group(8);
                }
                if(match.group(9) != null) {
                    arg1 = match.group(9);
                }
            }
            else if (match.group(2) != null && match.group(2).contains("goto")) {
                if(match.group(3) != null) {
                    opCode = match.group(3);
                }
                if(match.group(4) != null) {
                    arg1 = match.group(4); //???
                }
            }
            else if (match.group(2) != null && match.group(2).contains("loop")) {
                if(match.group(8) != null) {
                    opCode = match.group(8);
                }
                if(match.group(9) != null) {
                    arg1 = match.group(9);
                }
            }
            else if(match.group(2) != null && match.group(2).contains("end")) {
                if(match.group(10) != null) {
                    opCode = match.group(10);
                }
            }
        }
    }

    public String getLabel() {
        return label;
    }

    public String getOpcode() {
        return opCode;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int t) {
        target = t;
    }

    public int getPc() {
        return pc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int c) {
        count = c;
    }
}
