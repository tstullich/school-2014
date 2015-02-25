import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Interpreter {

public static void main(String[] args) {
    String regex = "([-+]?[0-9]*\\.?[0-9]+[\\/\\+\\-\\*])+([-+]?[0-9]*\\.?[0-9]+)";
    String input = "";
    try {
        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(System.in));
        System.out.print("-> ");
        while(!(input = reader.readLine()).equals("quit")) {
            System.out.println(input);
            if (input.matches(regex)) {
                System.out.println("Valid expession");
            } else {
                System.out.println("Invalid expression");
            }
            System.out.print("-> ");
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    
    System.out.println("Bye");
    }
}
