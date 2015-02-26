import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {

private final static Pattern numbers = Pattern.compile("([-+]?[0-9]*\\.?[0-9]+)[ +]+[\\/\\+\\-\\*]+[ +]+([-+]?[0-9]*\\.?[0-9]+)");
private final static Pattern operator = Pattern.compile("([-+/*])");

public static void execute(String input) throws Exception {
    Matcher numbersMatcher = numbers.matcher(input);
    Matcher operatorMatcher = operator.matcher(input);
    if (!numbersMatcher.find()) {
        throw new Exception("Invalid number");
    } else if (!operatorMatcher.find()) {
        throw new Exception("Invalid operator");
    }
    else {
        float firstNumber = Float.parseFloat(numbersMatcher.group(1));
        float secondNumber = Float.parseFloat(numbersMatcher.group(2));
        float result = 0;
        String operator = operatorMatcher.group(1);
        switch(operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "/":
                result = firstNumber / secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            default: throw new Exception("Unexpected error occured");
        }
        System.out.println(result);
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
        System.out.println(ex.getMessage());
    }

    System.out.println("Bye");
    }
}
