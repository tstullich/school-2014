import java.lang.Math;
import java.lang.StringBuilder;
import java.util.AbstractMap;
import java.util.Map;

public class Aligner {
    private boolean edited;
    private String tempString1;
    private String tempString2;
    private int[][] alignmentTable;
    private static int CASE_MISMATCH_COST = 2;
    private static int GAP_COST = 3;
    private static int MISMATCH_COST = 5;

    public Aligner() {
        // Initializes a 0x0 table in order to not throw
        // errors if align or newAlign have not been called
        alignmentTable = new int[0][0];
        edited = false;
    }

    public void align(String string1, String string2) {
        alignmentTable = new int[string1.length()][string2.length()];
        tempString1 = string1;
        tempString2 = string2;
        for (int i = 1; i < string1.length(); i++) {
            for (int j = 1; j < string2.length(); j++) {
                // Strings match exactly. Use diagonal entry
                if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
                    alignmentTable[i][j] = alignmentTable[i - 1][j - 1];
                }
                // No match. Need to introduce gap
                else {
                    alignmentTable[i][j] = findMax(i, j);
                }
            }
        }
    }

    public void newAlign(String string1, String string2) {
    }

    public int[][] getTable() {
        for (int i = 0; i < tempString1.length(); i++) {
            for(int j = 0; j < tempString2.length(); j++) {
                System.out.print(alignmentTable[i][j] + " ");
            }
            System.out.println();
        }
        return alignmentTable;
    }

    public Map.Entry<String, String> getAlignment() {
        // If strings have not been aligned yet just return the strings
        Map.Entry<String, String> alignedStrings;
        if (!edited) {
            alignedStrings = new AbstractMap.SimpleEntry<String, String>(tempString1, tempString2);
        }

        // Need to walk the table now to construct the answer
        int i = tempString1.length() + 1;
        int j = tempString2.length() + 1;
        StringBuilder builder = new StringBuilder();
        while (alignmentTable[i][j] != 0) {
            builder.append(alignmentTable[i][j]);
        }
        return null;
    }

    // Finds the greatest number to fill into the given gap
    private int findMax(int i, int j) {
        int diagonal = alignmentTable[i - 1][j - 1] + MISMATCH_COST;
        int above = alignmentTable[i - 1][j] + GAP_COST;
        int behind = alignmentTable[i][j - 1] + GAP_COST;
        //System.out.println("Comparing " + diagonal + " " + above + " " + behind);
        int min1 = Math.min(diagonal, above);
        return Math.min(min1, behind);
    }
}
