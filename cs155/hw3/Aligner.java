import java.lang.Math;
import java.lang.StringBuilder;
import java.util.AbstractMap;
import java.util.Map;

// TODO If I have time, optimize this shit

public class Aligner {
    private boolean edited;
    private StringBuilder tempString1;
    private StringBuilder tempString2;
    private int[][] alignmentTable;
    private static int CASE_MISMATCH_COST = 2;
    private static int GAP_COST = 3;
    private static int MISMATCH_COST = 5;

    public Aligner() {
        // Initializes a 0x0 table in order to not throw
        // errors if align or newAlign have not been called
        alignmentTable = new int[2][2];
        edited = false;
    }

    public void align(String string1, String string2) {
        alignmentTable = new int[string1.length() + 1][string2.length() + 1];
        tempString1 = new StringBuilder(string1);
        tempString2 = new StringBuilder(string2);
        for (int i = 1; i < string1.length() + 1; i++) {
            for (int j = 1; j < string2.length() + 1; j++) {
                // Strings match exactly. Use diagonal entry
                if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
                    alignmentTable[i][j] = alignmentTable[i - 1][j - 1];
                }
                // No match. Need to introduce gap
                else {
                    alignmentTable[i][j] = findMin(i, j);
                }
            }
        }
        edited = true;
    }

    public void newAlign(String string1, String string2) {
        alignmentTable = new int[string1.length() + 1][string2.length() + 1];
        tempString1 = new StringBuilder(string1);
        tempString2 = new StringBuilder(string2);
        for (int i = 1; i < string1.length() + 1; i++) {
            for (int j = 1; j < string2.length() + 1; j++) {
                char c1 = string1.charAt(i - 1);
                char c2 = string2.charAt(j - 1);
                // Characters match exactly. Use diagonal entry
                if (c1 == c2) {
                    alignmentTable[i][j] = alignmentTable[i - 1][j - 1];
                }
                // Check if we can just change case of the character
                else if(String.valueOf(c1).compareToIgnoreCase(String.valueOf(c2)) == 0) {
                    alignmentTable[i][j] = alignmentTable[i - 1][j - 1] + CASE_MISMATCH_COST;
                }
                // No match. Need to introduce gap
                else {
                    alignmentTable[i][j] = findMin(i, j);
                }
            }
        }
        edited = true;
    }

    public int[][] getTable() {
        return alignmentTable;
    }

    public Map.Entry<String, String> getAlignment() {
        // If strings have not been aligned yet just return two empty strings
        if (!edited) {
            return new AbstractMap.SimpleEntry<String, String>("", "");
        }

        // Need to walk the table now to construct the answer
        int i = tempString1.length();
        int j = tempString2.length();
        Map.Entry<String, String> alignedStrings;
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        while (((tempString1.length() > 0) && (tempString2.length() > 0)) && ((i != 0) && (j != 0)))  {
            Map.Entry<Integer, Integer> nextValue = findMinTraceback(i, j);
            // Next value matches diagonal entry. Check for case mismatch
            if (((i - 1) == nextValue.getKey()) && ((j - 1) == nextValue.getValue())) {
                char c1 = tempString1.charAt(tempString1.length() - 1);
                char c2 = tempString2.charAt(tempString2.length() - 1);
                // No case mismatch. Proceed with using diagonal entry
                if (alignmentTable[i - 1][j - 1] - alignmentTable[i][j] == 0) {
                    s1.append(c1);
                    s2.append(c2);

                }
                // Case mismatch. Change the case of the entry to upper
                else {
                    s1.append(Character.toUpperCase(c1));
                    s2.append(Character.toUpperCase(c2));
                }
                tempString1.deleteCharAt(tempString1.length() - 1);
                tempString2.deleteCharAt(tempString2.length() - 1);
            }
            // Entry above has the lowest value. Need to shift sequence 2
            else if ((i == nextValue.getKey()) && ((j - 1) == nextValue.getValue())) {
                s1.append(tempString1.charAt(tempString1.length() - 1));
                s2.append("_");

                tempString1.deleteCharAt(tempString1.length() - 1);
            }
            // Left entry has the lowest value. Need to shift sequence 1
            else {
                s1.append("_");
                s2.append(tempString2.charAt(tempString2.length() - 1));

                tempString2.deleteCharAt(tempString2.length() - 1);
            }

            i = nextValue.getKey();
            j = nextValue.getValue();
        }

        // Need to add on remaini)g characters that may not have shifted
        s1.append(tempString1.reverse());
        s2.append(tempString2.reverse());

        return new AbstractMap.SimpleEntry<String, String>(s1.reverse().toString(),
                                                           s2.reverse().toString());
    }

    // Finds the entry in the table that should be traced back to next
    // and returns a pair of the width and height coordinate
    private Map.Entry<Integer, Integer> findMinTraceback(int i, int j) {
        int diagonal = alignmentTable[i - 1][j - 1];
        int above = alignmentTable[i - 1][j];
        int behind = alignmentTable[i][j - 1];

        // Diagonal entry is the lowest. Also solves tiebreaker in case
        // multiple entries have the same value. Diagonal always comes first
        if ((diagonal <= above) && (diagonal <= behind)) {
            return new AbstractMap.SimpleEntry<Integer, Integer>(i - 1, j - 1);
        }
        // Above entry is the lowest
        else if ((above < diagonal) && (above < behind)) {
            return new AbstractMap.SimpleEntry<Integer, Integer>(i - 1, j);
        }
        // Entry to the left of the table is the lowest
        else {
            return new AbstractMap.SimpleEntry<Integer, Integer>(i, j - 1);
        }
    }

    // Finds the greatest number to fill into the given gap
    private int findMin(int i, int j) {
        int diagonal = alignmentTable[i - 1][j - 1] + MISMATCH_COST;
        int above = alignmentTable[i - 1][j] + GAP_COST;
        int behind = alignmentTable[i][j - 1] + GAP_COST;
        int min1 = Math.min(diagonal, above);
        return Math.min(min1, behind);
    }
}
