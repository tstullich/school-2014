import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {

private final static Pattern terms = Pattern.compile("([-+]?[0-9]*\\.?[0-9]+)[ +]+[\\/\\+\\-\\*]+[ +]+([-+]?[0-9]*\\.?[0-9]+)");
private final static Pattern operator = Pattern.compile("([-+/*])");

public static void execute(String input) throws Exception {
    System.out.println(input);
    Matcher termsMatcher = terms.matcher(input);
    Matcher operatorMatcher = operator.matcher(input);
    if ((termsMatcher.find()) && (operatorMatcher.find())) {
        System.out.println("Valid expression");

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
        System.out.println("Trouble");
    }
    
    System.out.println("Bye");
    }
}
