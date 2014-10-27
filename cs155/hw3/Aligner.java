import java.util.Map;

public class Aligner {
    private int[][] alignmentTable;

    public Aligner() {
        alignmentTable = null; 
    }

    public void align(String string1, String string2) {
    }
    
    public void newAlign(String string1, String string2) {
    }

    public int[][] getTable() {
        // If no strings have been aligned yet return null
        if (alignmentTable == null) {
            return null;
        }
        return null;
    }

    public Map.Entry<String, String> getAlignment() {
        return null;
    }
}
