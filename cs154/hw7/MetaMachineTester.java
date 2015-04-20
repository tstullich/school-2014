package meta;

public class MetaMachineTester {
    public static void main(String[] args) {
        MetaMachine mm = new MetaMachine();
        try {
            mm.execute("meta.Greeter", "greetings", "Hello", "Jupiter");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
