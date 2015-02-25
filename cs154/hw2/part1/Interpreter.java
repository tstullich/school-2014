import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Interpreter {

private final static Pattern regex = Pattern.compile("([-+]?[0-9]*\\.?[0-9]+[ +]+[\\/\\+\\-\\*])+[ +]+([-+]?[0-9]*\\.?[0-9]+)");

public boolean isValidExp(String exp) {
    return false;
}

public static void execute(String input) throws Exception {
    System.out.println(input);
    if (input.matches(regex)) {
        System.out.println("Valid expession");
    } else {
        System.out.println("Invalid expression");
    }
}

public static void main(String[] args) {
    String input = "";
    try {
        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(System.in));
        System.out.print("-> ");
        while(!(input = reader.readLine()).equals("quit")) {
            execute(input);
            System.out.print("-> ");
        }
    } catch (Exception ex) {
    }
    
    System.out.println("Bye");
    }
}
