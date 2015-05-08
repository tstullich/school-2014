import java.util.regex.*;

public class Command {
    private String label;
    private String opCode;
    private String arg1;
    private String arg2;
    private int target;
    private int pc;
    private int count; // used for the loop command
    private static String pattern = "([A-Z]+[: ]{1}){0,1}(load |goto |inc |loop |end ){0,1}([a-z]+ ){0,1}([0-9]+){0,1}";
    private static Pattern cmmdPattern;

    public Command(String command, int pc) {
        System.out.println(command);
        Pattern cmmdPattern = Pattern.compile(pattern);
        Matcher m = cmmdPattern.matcher(command);

        if (m.find()) {
            if (m.group(0) != null) {
                System.out.print("0. ");
                System.out.println(m.group(0));
                label = m.group(0);
            }
            if (m.group(1) != null) {
                System.out.print("1. ");
                System.out.println(m.group(1));
                opCode = m.group(1);
            }
            if (m.group(2) != null) {
                System.out.print("2. ");
                System.out.println(m.group(2));
                arg1 = m.group(2);
            }
            if (m.group(3) != null) {
                System.out.print("3. ");
                System.out.println(m.group(3));
                arg2 = m.group(3);
            }
        }

        this.pc = pc;
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
